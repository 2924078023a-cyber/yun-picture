<script setup lang="ts">
import VChart from 'vue-echarts'
import 'echarts'
import { ref, watchEffect } from 'vue'
import { getSpaceUsageAnalyzeUsingPost } from '@/api/spaceAnalyzeController.ts'
import { message } from 'ant-design-vue'
import { formatSize } from '@/utils/index.ts'

interface Props {
  queryAll?: boolean
  queryPublic?: boolean
  spaceId?: number
}

const props = withDefaults(defineProps<Props>(), {
  queryAll: false,
  queryPublic: false,
})
//图表数据
const data = ref<API.SpaceUsageAnalyzeResponse>({})

//加载
const loading = ref(false)

//获取数据
const fetchData = async () => {
  loading.value = true
  const res = await getSpaceUsageAnalyzeUsingPost({
    queryAll: props.queryAll,
    queryPublic: props.queryPublic,
    spaceId: props.spaceId,
  })
  if (res.data.code === 0 && res.data.data) {
    data.value = res.data.data
  } else {
    message.error('获取数据失败，' + res.data.message)
  }
  loading.value = false
}

//监听数据
watchEffect(() => {
  fetchData()
})
</script>

<template>
  <div class="space-user-analyze">
    <a-flex gap="middle">
      <a-card title="存储空间" style="width: 50%">
        <div style="height: 320px; text-align: center">
          <h3>
            {{ formatSize(data.usedSize) || 0 }} /
            {{ data.maxSize ? formatSize(data.maxSize) : '无限制' }}
          </h3>
          <a-progress type="dashboard" :percent="data.sizeUsageRatio ?? 0" />
        </div>
      </a-card>
      <a-card title="图片数量" style="width: 50%">
        <div style="height: 320px; text-align: center">
          <h3>{{ data.usedCount || 0 }} / {{ data.maxCount ?? '无限制' }}</h3>
          <a-progress type="dashboard" :percent="data.countUsageRatio ?? 0" />
        </div>
      </a-card>
    </a-flex>
  </div>
</template>

<style scoped></style>
