package com.yun.yunpicturebackend.manager.auth;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yun.yunpicturebackend.manager.auth.model.SpaceUserAuthConfig;
import com.yun.yunpicturebackend.manager.auth.model.SpaceUserPermissionConstant;
import com.yun.yunpicturebackend.manager.auth.model.SpaceUserRole;
import com.yun.yunpicturebackend.model.entity.Space;
import com.yun.yunpicturebackend.model.entity.SpaceUser;
import com.yun.yunpicturebackend.model.entity.User;
import com.yun.yunpicturebackend.model.enums.SpaceRoleEnum;
import com.yun.yunpicturebackend.model.enums.SpaceTypeEnum;
import com.yun.yunpicturebackend.service.SpaceUserService;
import com.yun.yunpicturebackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class SpaceUserAuthManager {

    @Resource
    private SpaceUserService spaceUserService;

    @Resource
    private UserService userService;

    public static final SpaceUserAuthConfig SPACE_USER_AUTH_CONFIG;

    static {
        String json = ResourceUtil.readUtf8Str("biz/spaceUserAuthConfig.json");
        SPACE_USER_AUTH_CONFIG = JSONUtil.toBean(json, SpaceUserAuthConfig.class);
    }

    /**
     * 根据用户获取权限列表
     *
     * @param
     * @return 权限信息
     */
        public List<String> getPermissionsByRole(String spaceUserRole) {
            if (StrUtil.isBlank(spaceUserRole)) {
                return new ArrayList<>();
            }
            // 2. 防御性判空：核心修复点 (利用 Hutool)
            List<SpaceUserRole> roles = SPACE_USER_AUTH_CONFIG.getRoles();
            if (CollUtil.isEmpty(roles)) {
                // 如果配置没读取到，或者配置列表为空，直接返回空权限
                return new ArrayList<>();
            }
            SpaceUserRole role = SPACE_USER_AUTH_CONFIG.getRoles()
                    .stream()
                    .filter(r -> spaceUserRole.equals(r.getKey()))
                    .findFirst()
                    .orElse(null);
            if (role == null) {
                return new ArrayList<>();
            }
            // 5. 返回角色的权限列表 (保险起见，这里也可以加个判空)
            return role.getPermissions() != null ? role.getPermissions() : new ArrayList<>();
        }

    /**
     * 根据用户获取权限列表
     *
     * @param
     * @return 角色信息
     */
    public List<String> getPermissionList(Space space, User loginUser) {
        if (loginUser == null) {
            return new ArrayList<>();
        }
        //管理员权限
        List<String> ADMIN_PERMISSIONS = getPermissionsByRole(SpaceRoleEnum.ADMIN.getValue());
        //公共图库
        if (space == null) {
            if (userService.isAdmin(loginUser)) {
                return ADMIN_PERMISSIONS;
            }
            return Collections.singletonList(SpaceUserPermissionConstant.PICTURE_VIEW);
        }
        SpaceTypeEnum spaceTypeEnum = SpaceTypeEnum.getEnumByValue(space.getSpaceType());
        if (spaceTypeEnum == null) {
            return new ArrayList<>();
        }
        //根据空间获取对应的权限
        switch (spaceTypeEnum) {
            case PRIVATE:
                //私有空间，仅本人或管理员有所有权限
                if (space.getUserId().equals(loginUser.getId()) || userService.isAdmin(loginUser)) {
                    return ADMIN_PERMISSIONS;
                } else {
                    return new ArrayList<>();
                }
            case TEAM:
                //团队空间，查询SpaceUser并获取角色和权限
                SpaceUser spaceUser = spaceUserService.lambdaQuery()
                        .eq(SpaceUser::getSpaceId, space.getId())
                        .eq(SpaceUser::getUserId, loginUser.getId())
                        .one();
                log.info("团队空间权限查询 - 空间ID: {}, 用户ID: {}, 查询结果: {}", 
                    space.getId(), loginUser.getId(), spaceUser != null ? spaceUser.getSpaceRole() : "null");
                if (spaceUser == null) {
                    return new ArrayList<>();
                } else {
                    List<String> permissions = getPermissionsByRole(spaceUser.getSpaceRole());
                    log.info("角色 {} 对应的权限: {}", spaceUser.getSpaceRole(), permissions);
                    return permissions;
                }
        }
        return new ArrayList<>();
    }


}
