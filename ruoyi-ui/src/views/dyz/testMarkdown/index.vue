<template>
  <el-container>
    <!-- 侧边栏 -->
    <el-aside>
      <el-tree 
      :data="treeRoot" 
      :props="defaultProps" 
      @node-click="handleNodeClick"
      :load="loadNode"
      lazy
      ></el-tree>
    </el-aside>
    <!-- 主要区域 -->
    <el-main>
      <v-md-preview :text="text"></v-md-preview>
    </el-main>
  </el-container>


</template>

<script>
  import giteeUtil from '@/utils/gitee/common.js'
  export default {
      data() {
        return {
          text: '',
          treeRoot: []
        };
      },
      methods:{
        getContent(){
          giteeUtil.getFileContent('fun_zil','note','/web3/web3.md')
          .then((res)=>{
            let content = res.data.content;
            this.text = content;
          });

        },
        getTree(node,resolve){
          giteeUtil.getTree('fun_zil','note','master')
          .then((res)=>{
            let subTreeResponse = res.data.tree;
            let childrens = [];
            for(var i=0;i<subTreeResponse.length;i++){
              let node = {};
              let subTreeNode = subTreeResponse[i];
              node.label = subTreeNode.path;
              node.sha = subTreeNode.sha;
              if(subTreeNode.mode === '40000'){
                node.isLeaf = false;
              }else{
                node.isLeaf = true;
              }
              childrens.push(node);
            }
            treeNode.children=childrens;
            return res;
          });
        },
        async loadNode(node,resolve){
          
        }
      },
      created() {
        this.getContent();
        this.getTree();
      }
    };
</script>

<style>
</style>
