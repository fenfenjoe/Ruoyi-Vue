<!-- 表单常用写法 -->


<template>
  <div class="app-container">
    <!-- 表单 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true">
      <!-- 表单项 -->
      <el-form-item label="角色名称" prop="roleName">
        <!-- v-model 绑定到queryParams.roleName这个属性。用户输入，roleName的值会跟着变动-->
        <!-- style 样式-->
        <!-- @keyup.enter.native 回车触发函数-->
        <el-input
          v-model="queryParams.roleName"
          placeholder="请输入角色名称"
          clearable
          style="width: 240px"
        />

      </el-form-item>

    </el-form>


  </div>


</template>



<script>
export default{
  name: 'dyz_test',
  
  //页面参数
  data(){
    return{
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleName: undefined,
        roleKey: undefined,
        status: undefined
      }
    }
  },
  //页面可用方法
  methods:{
    
  }
}

</script>

<style scoped>

</style>
