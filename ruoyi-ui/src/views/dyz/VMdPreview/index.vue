<template>
  <el-container>
    <!-- 侧边栏 -->
    <el-aside>
      <el-tree
      :data="treeRoot"
      :props="treeProps"
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
  import giteeService from '@/api/dyz/testMarkdown/giteeService.js';
  export default {
      data() {
        return {
          text: '',
          treeRoot: [],
          treeProps: {
            label: 'path',
            children: 'children',
            isLeaf:'isLeaf'
          }
        };
      },
      methods:{
        initTree(treeNode){
          this.loadNode();
        },
        loadNode(treeNode,resolve){
          let param = {};
          if(treeNode == undefined){
            param.sha = 'master';
          }else{
            // param.sha = treeNode.data.sha;
            param.url = treeNode.data.url;
          }

          giteeService.getTreeContent(param)
          .then((res)=>{
            let subTreeResponse = res.data.tree;
            let childrens = [];
            for(var i=0;i<subTreeResponse.length;i++){
              let node = {};
              let subTreeNode = subTreeResponse[i];
              node.path = subTreeNode.path;
              node.sha = subTreeNode.sha;
              node.url = subTreeNode.url;
              if(subTreeNode.type === 'tree'){
                node.isLeaf = false;
              }else{
                node.isLeaf = true;
              }
              childrens.push(node);
            }

            if(treeNode == null){
              this.treeRoot = childrens;
            }else{
              setTimeout(() => {
                  resolve(childrens);
                }, 500);
            }
            return res;
          });
        },
        handleNodeClick(data){
          if(data.isLeaf){
            let param = {};
            // param.path = data.path;
            param.url = data.url;
            giteeService.getFileContent(param)
              .then((res)=>{
                let base64Content = res.data.content;
                let content = Buffer.from(base64Content,'base64').toString('utf8');
                this.text = content;
              });
          }
        }
      },
      //初始化后，加载树节点
      created() {
        this.initTree();
      }
    };
</script>

<style>
</style>
