<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { message } from 'ant-design-vue'
import dayjs from 'dayjs'
import {
  listPictureTagCategoryUsingGet,
  searchPictureByColorUsingPost,
} from '@/api/pictureController.ts'
import PictureQueryRequest = API.PictureQueryRequest


interface Props {
  onSearch?: (searchParams: PictureQueryRequest) => void
}

const props = defineProps<Props>()

// 搜索条件
const searchParams = reactive<API.PictureQueryRequest>({})
// 搜索数据
const doSearch = () => {
  props.onSearch?.(searchParams)
}

const dateRange = ref<[]>([])

const onRangeChange = (dates: any[], dateStrings: string[]) => {
  if (dates.length < 2) {
    searchParams.startEditTime = undefined
    searchParams.endEditTime = undefined
  } else {
    searchParams.startEditTime = dates[0].toDate()
    searchParams.endEditTime = dates[1].toDate()
  }
}
// 时间范围预设
const rangePresets = ref([
  { label: '过去 7 天', value: [dayjs().add(-7, 'd'), dayjs()] },
  { label: '过去 14 天', value: [dayjs().add(-14, 'd'), dayjs()] },
  { label: '过去 30 天', value: [dayjs().add(-30, 'd'), dayjs()] },
  { label: '过去 90 天', value: [dayjs().add(-90, 'd'), dayjs()] },
])

const categoryOptions = ref<string[]>([])
const tagOptions = ref<string[]>([])

const getTagCategoryOptions = async () => {
  const res = await listPictureTagCategoryUsingGet()
  if (res.data.code === 0 && res.data.data) {
    tagOptions.value = (res.data.data.tagList ?? []).map((data: string) => {
      return {
        value: data,
        label: data,
      }
    })
    categoryOptions.value = (res.data.data.categoryList ?? []).map((data: string) => {
      return {
        value: data,
        label: data,
      }
    })
  } else {
    message.error('加载选项失败，' + res.data.message)
  }
}

onMounted(() => {
  getTagCategoryOptions()
})

const doClear = () => {
  Object.keys(searchParams).forEach((key) => {
    searchParams[key] = undefined
  })
  dateRange.value = []
  // 重新搜索
  props.onSearch?.(searchParams)
}

</script>

<template>
  <div class="picture-search-form">
    <!-- 搜索表单 -->

    <a-form layout="inline" :model="searchParams" @finish="doSearch">
      <a-form-item label="关键词" name="searchText">
        <a-input
          v-model:value="searchParams.searchText"
          placeholder="从名称和简介搜索"
          allow-clear
        />
      </a-form-item>
      <a-form-item label="分类" name="category">
        <a-auto-complete
          style="min-width: 96px"
          v-model:value="searchParams.category"
          :options="categoryOptions"
          placeholder="请输入分类"
          allowClear
        />
      </a-form-item>
      <a-form-item label="标签" name="tags">
        <a-select
          v-model:value="searchParams.tags"
          :options="tagOptions"
          mode="tags"
          placeholder="请输入标签"
          allowClear
          style="min-width: 96px"
        />
      </a-form-item>
      <a-form-item label="日期" name="dataRange">
        <a-range-picker
          show-time
          style="width: 300 px"
          v-model:value="dateRange"
          :placeholder="['编辑开始日期', '编辑结束时间']"
          format="YYYY/MM/DD HH:mm:ss"
          :presets="rangePresets"
          @change="onRangeChange"
        />
      </a-form-item>
      <a-form-item label="名称" name="name">
        <a-input v-model:value="searchParams.name" placeholder="请输入名称" allow-clear />
      </a-form-item>
      <a-form-item label="简介" name="introduction">
        <a-input v-model:value="searchParams.introduction" placeholder="请输入简介" allow-clear />
      </a-form-item>
      <a-form-item label="宽度" name="picWidth">
        <a-input-number v-model:value="searchParams.picWidth" />
      </a-form-item>
      <a-form-item label="高度" name="picHeight">
        <a-input-number v-model:value="searchParams.picHeight" />
      </a-form-item>
      <a-form-item label="格式" name="picFormat">
        <a-input v-model:value="searchParams.picFormat" placeholder="请输入格式" allow-clear />
      </a-form-item>
      <a-form-item>
        <a-space>
          <a-button type="primary" html-type="submit" style="width: 96px">搜索</a-button>
          <a-button html-type="reset" @click="doClear" style="width: 96px">重置</a-button>
        </a-space>
      </a-form-item>
    </a-form>

  </div>
</template>

<style scoped>
.picture-search-form .ant-form-item {
  margin-top: 16px;
}
</style>
