<template>
    <el-card style="width: 500px">
    <el-form label-width="80px" size="small" >
        <el-upload
                class="avatar-uploader"
                action="http://localhost:8888/file/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess">
            <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <el-form-item label="用户名">
            <el-input v-model="form.username" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
            <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
            <el-input v-model="form.telephone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
       </el-form-item>
    </el-form>
    </el-card>
</template>

<script>
    export default {
        name: "Person",
        data(){
            return{
                form: {},
                user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            }
        },
        created() {
           /*封装一个方法
           // this.request.get("/user/username/"+this.user.username).then(res => {
            //  if (res.code === '200'){
            //      console.log(res)
            //      this.form = res.data
            //  }
            // })*/
            this.getUser().then(res =>{
                console.log(res)
                this.form = res
            })
        },
        methods: {
            async getUser(){
                return   (await this.request.get("/user/username/"+this.user.username)).data
        },
              /*这是一个异步的 变同步要用async
               // getUser(){
               //     this.request.get("/user/username/"+this.user.username).then(res => {
               //               if (res.code === '200'){
               //                   console.log(res)
               //                   this.form = res.data
               // }
               //     })
               //               },*/
            save(){
                this.request.post("/user/",this.form).then( res =>{
                    if (res.code==='200'){
                        this.$message.success("保存成功")

                        //触发父级更新User用户数据的方法
                        this.$emit("refreshUser")

                //  子传父 父传子 Person->Manage  Manage -> Header 实现头像同步变化
                        //更新浏览器存储的用户信息
                        this.getUser().then(res =>{
                        res.token =JSON.parse(localStorage.getItem("user")).token
                        localStorage.setItem("user",JSON.stringify(res))
                        })
                    }else {
                        this.$message.error("保存失败")
                    }
                })
            },
            handleAvatarSuccess(res){
                this.form.avatarUrl = res
            }
        }
    }
</script>
<!--lang="less" scoped-->
<style>
  /*vue深度选择器实现图片居中和控间距*/
  /*  /deep/.avatar-uploader {text-align: center;padding-bottom: 10px}*/
  .avatar-uploader {text-align: center;padding-bottom: 10px}
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }
    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>