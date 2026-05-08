import { saveAs } from 'file-saver'

export const formatSize = (size: number) => {
  if (!size) {
    return '未知'
  }
  if (size < 1024) {
    return size + 'B'
  }
  if (size < 1024 * 1024) {
    return (size / 1024).toFixed(2) + 'KB'
  }
  return (size / (1024 * 1024)).toFixed(2) + 'MB'
}

export function doDownloadImage(url?: string, fileName?: string) {
  if (!url) {
    return
  }
  saveAs(url,fileName)
}

export function toHexColor(input: string){
  if (!input) {
    return 'transparent'
  }
  try{
  //去掉0x前缀
  const colorValue = input.startsWith('0x') ? input.slice(2) : input

    // 3. 🛡️ 第二层防线：尝试解析
    // 如果 input 不是合法的 16 进制（比如是 "-"），parseInt 会返回 NaN
    const decimalValue = parseInt(colorValue, 16)
    if (isNaN(decimalValue)) {
      return 'transparent'
    }

    // 将剩余部分解析为 16 进制，再转成 6 位 16 进制字符串
    const hexColor = decimalValue.toString(16).padStart(6, '0')
  //返回标准#RRGGBB格式
  return `#${hexColor}`
    }
  catch ( e){
    return 'transparent'
  }
}
