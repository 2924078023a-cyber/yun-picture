<script setup lang="ts">
import GlobalHeader from '@/components/GlobalHeader.vue'
import GlobalSider from '@/components/GlobalSider.vue'
</script>

<template>
  <div id="BasicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader> </GlobalHeader>
      </a-layout-header>
      <a-layout>
        <GlobalSider class="sider" />
        <a-layout-content class="content">
          <router-view />
        </a-layout-content>
      </a-layout>
      <a-layout-footer class="footer">
        <a href="http://www.codefather.cn" target="_blank"> 编程导航 </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#BasicLayout .header {
  background: white;
  color: unset;
  margin-bottom: 1px;
  padding-inline: 20px;
}

#BasicLayout .sider {
  background: #fff;
  border-right: 0.5px solid #eee;
  padding-top: 20px;
}

#BasicLayout :deep (.ant-menu-root) {
  border-bottom: none !important;
  border-inline-end: none !important;
}

#BasicLayout .content {
  padding: 28px;
  background:  #fff;
  margin-bottom: 28px;
}

#BasicLayout .footer {
  background: #efefef;
  padding: 16px;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
