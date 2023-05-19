import request from '@/utils/request'

// 查询树节点
export default {
  getTreeContent:function(query) {
    return request({
      url: '/gitee/getTreeContent',
      method: 'get',
      params: query
    })
  },
  getFileContent:function(query) {
    return request({
      url: '/gitee/getFileContent',
      method: 'get',
      params: query
    })
  }
}
