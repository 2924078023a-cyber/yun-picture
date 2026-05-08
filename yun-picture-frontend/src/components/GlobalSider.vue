<template>
  <div>
    <a-layout-sider
      v-if="loginUserStore.loginUser.id"
      width="200"
      breakpoint="lg"
      collapsed-width="0"
    >
      <a-menu
        mode="inline"
        v-model:selectedKeys="current"
        :items="menuItems"
        @click="doMenuClick"
      />
    </a-layout-sider>
  </div>
</template>

<script lang="ts" setup>
import { computed, h, ref, watchEffect } from 'vue'
import { PictureOutlined, UserOutlined, TeamOutlined } from '@ant-design/icons-vue'
import { useRouter } from 'vue-router'
import { useLoginUserStore } from '@/stores/useLoginUserStore.ts'
import { SPACE_TYPE_ENUM } from '@/constants/space.ts'
import { listMyTeamSpaceUsingPost } from '@/api/spaceUserController.ts'
import { message } from 'ant-design-vue'

const loginUserStore = useLoginUserStore()

//固定的菜单列表
const fixedMenuItems = [
  {
    key: '/',
    label: '公共图库',
    icon: () => h(PictureOutlined),
  },
  {
    key: '/my_space',
    label: '我的空间',
    icon: () => h(UserOutlined),
  },
  {
    key: '/add_space?type=' + SPACE_TYPE_ENUM.TEAM,
    label: '创建团队',
    icon: () => h(TeamOutlined),
  },
]

const teamSpaceList = ref<API.SpaceUserVO[]>([])
const menuItems = computed(() => {
  if (teamSpaceList.value.length < 1) {
    return fixedMenuItems
  }

  // 👉 新增防守逻辑：过滤掉关联空间已经被物理删除的“脏数据”
  const validTeamSpaces = teamSpaceList.value.filter((spaceUser) => spaceUser.space != null)

  // 展示团队空间分组 (注意这里改成遍历 validTeamSpaces)
  const teamSpaceSubMenus = validTeamSpaces.map((spaceUser) => {
    const space = spaceUser.space
    return {
      key: '/space/' + spaceUser.spaceId,
      label: space?.spaceName,
    }
  })

  const teamSpaceMenuGroup = {
    type: 'group',
    label: '我的团队',
    key: 'team_space',
    children: teamSpaceSubMenus,
  }

  return [...fixedMenuItems, teamSpaceMenuGroup]
})
//加载团队空间列表
const fetchTeamSpaceList = async () => {
  const res = await listMyTeamSpaceUsingPost()
  if (res.data.code === 0 && res.data.data) {
    teamSpaceList.value = res.data.data
  } else {
    message.error('加载我的团队空间失败，' + res.data.message)
  }
}
/**
 * 监听变量
 */
watchEffect(() => {
  //登陆时才加载
  if (loginUserStore.loginUser.id) {
    fetchTeamSpaceList()
  }
})

const router = useRouter()
const current = ref<string[]>([])

router.afterEach((to, from, failure) => {
  current.value = [to.path]
})

const doMenuClick = ({ key }: { key: string }) => {
  router.push(key)
}
</script>
<style scoped>
#GlobalSider .ant-layout-sider {
  background: none;
}
</style>
