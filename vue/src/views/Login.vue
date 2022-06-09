<template>
    <div class="wrapper">
        <div class="main">
            <!-- 整个注册盒子 -->
            <div class="loginbox">
                <!-- 左侧的注册盒子 -->
                <div class="background">
                    <div class="title" >Welcome to System Management Center</div>
                </div>

                <!-- 右侧的注册盒子 -->
                <div class="loginbox-in">

                    <el-form :model="user" :rules="rules" ref="userForm">
                        <el-form-item prop="username">
                            <div class="userbox">
                                <span class="iconfont">&#58905;</span>
                                <el-input v-model="user.username" placeholder="用户名"></el-input>
                            </div>
                        </el-form-item>
                        <br>
                        <el-form-item prop="password">
                            <div class="pwdbox">
                                <span class="iconfont">&#58933;</span>
                                <el-input show-password v-model="user.password" placeholder="密码"></el-input>
                            </div>
                        </el-form-item>
                        <br>
                        <el-form-item >
                            <el-button  class="login_btn" autocomplete="off" @click="login">登入</el-button>
                            <el-button  class="register_btn" autocomplete="off" @click="$router.push('/register')">注册</el-button>
                        </el-form-item>
                    </el-form>
                </div>

            </div>
        </div>
    </div>
</template>

<script>
    import {setRoutes} from "@/router";

    export default {
        name: "Login",
        data() {
            return {
                user: {},
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 2, max: 14, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            login() {
                this.$refs['userForm'].validate((valid) => {
                    if (valid) {  // 表单校验合法
                        this.request.post("/user/login", this.user,).then(res => {
                            if(res.code == '200') {
                                localStorage.setItem("user",JSON.stringify(res.data)) //存储用户信息到浏览器
                                localStorage.setItem("menus",JSON.stringify(res.data.menus))//存储菜单列表信息到浏览器
                                //动态设置当前用户的路由
                                setRoutes()
                                this.$message.success("登入成功")
                              if (res.data.role === 'ROLE_STUDENT') {
                                this.$router.push("/front/home")
                              } else {
                                this.$router.push("/")
                              }
                            } else {
                            this.$message.error(res.msg)
                            }
                        })
                    }
                });
            }
        }
    }
</script>

<style lang="less" scoped>
  .wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right,  #97c4b3,#468870);
    overflow: hidden;
  }
  .loginbox{
    display:flex;
    position:absolute;
    width:800px;
    height:400px;
    top:40%;
    left:50%;
    transform:translate(-50%,-50%);
    box-shadow: 0 12px 16px 0  rgba(0,0,0,0.24), 0 17px 50px 0 #4E655D;
  }
  .loginbox-in{
    background-color:#89AB9E;
    width:240px;
  }

  /*.userbox>>> .el-input__inner {*/
  /deep/.el-input__inner {
    //border-bottom:1px solid #E9E9E9;
    background-color: rgba(255,255,255,0.247)!important; }//transparent
  .userbox{
    margin-top:120px ;
    height:30px;
    width:200px;
    display: flex;
    margin-left:25px;
  }
  .pwdbox{
    height:30px;
    width:200px;
    display: flex;
    margin-left:25px;

  }

  .background{
    width:570px;
    background-image:url('./img/Christmas_Trees.png');
    background-size:cover;
    font-family:sans-serif;
  }
  .title{
    margin-top:320px;
    font-weight:bold;
    font-size:20px;
    color:#4E655D;
    text-align: center;
  }
  .title:hover{
    font-size:21px;
    transition: all 0.4s ease-in-out;
    cursor: pointer;
  }
  /*.uesr-text{*/
  /*    position:left;*/
  /*}*/
  el-input{
    outline-style: none ;
    border: 0;
    border-bottom:1px solid #E9E9E9;
    background-color:transparent;
    height:20px;
    font-family:sans-serif;
    font-size:15px;
    color:#445b53;
    font-weight:bold;
  }
  /deep/input::-webkit-input-placeholder{
    color:#E9E9E9;
  }
  el-input:focus{
    border-bottom:2px solid #445b53;
    background-color:transparent;
    transition: all 0.2s ease-in;
    font-family:sans-serif;
    font-size:15px;
    color:#445b53;
    font-weight:bold;

  }
  el-input:hover{
    border-bottom:2px solid #445b53;
    background-color:transparent;
    transition: all 0.2s ease-in;
    font-family:sans-serif;
    font-size:15px;
    color:#445b53;
    font-weight:bold;

  }

  el-input:-webkit-autofill {
    /* 修改默认背景框的颜色 */
    box-shadow: 0 0 0px 1000px  #89AB9E inset !important;
    /* 修改默认字体的颜色 */
    -webkit-text-fill-color: #445b53;
  }

  el-input:-webkit-autofill::first-line {
    /* 修改默认字体的大小 */
    font-size: 15px;
    /* 修改默认字体的样式 */
    font-weight:bold;
  }
  .log-box{
    font-size:12px;
    display: flex;
    justify-content: space-between ;
    width:190px;
    margin-left:30px;
    color:#4E655D;
    margin-top:-5px;
    align-items: center;

  }
  .log-box-text{
    color:#4E655D;
    font-size:12px;
    text-decoration:underline;
  }
  .login_btn{
    background-color: #5f8276; /* Green */
    border: none;
    color: #FAFAFA;
    padding: 5px 22px;
    text-align: center;
    text-decoration: none;
    font-size: 13px;
    border-radius: 15px;
    outline:none;
    margin-left:25px;
  }
  .login_btn:hover{
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    cursor: pointer;
    background-color: #0b5137;
    transition: all 0.2s ease-in;
  }

  .warn{
    margin-top:60px;
    /* margin-right:120px; */
    margin-left:-120px;
    margin-bottom: 5px;
    font-weight:bold;
    font-size:17px;
  }

  .register_btn{
    /*background-color: transparent; !* Green *!*/
    /*border: none;*/
    /*text-decoration: none;*/
    /*font-size: 15px;*/
    /* border-radius: 20px;*/
    /*!*color:#4E655D;*!*/
    /*font-size:12px;*/
    /*!*text-decoration:underline;*!*/
    /*display: flex;*/
    margin-left:40px;
    /*outline:none;*/
    /*background-color: rgb(42, 103, 81);*/
    background-color: #5f8276; /* Green */
    border: none;
    color: #FAFAFA;
    padding: 5px 22px;
    text-align: center;
    text-decoration: none;
    font-size: 13px;
    border-radius: 15px;
    outline:none;

  }
  .register_btn:hover{
    /*font-weight:bold;*/
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
    cursor: pointer;
    background-color: #0b5137;
    transition: all 0.2s ease-in;
  }
  @font-face {
    font-family: "iconfont"; /* Project id 3387991 */
    src: url('font/iconfont.eot?t=1652091954316'); /* IE9 */
    src: url('font/iconfont.eot?t=1652091954316#iefix') format('embedded-opentype'), /* IE6-IE8 */
    url('font/iconfont.woff2?t=1652091954316') format('woff2'),
    url('font/iconfont.woff?t=1652091954316') format('woff'),
    url('font/iconfont.ttf?t=1652091954316') format('truetype'),
    url('font/iconfont.svg?t=1652091954316#iconfont') format('svg');
  }

  .iconfont {
    font-family: "iconfont" !important;
    font-size: 20px;
    font-style: normal;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    height:22px;
    color:#4E655D;
    margin-right:10px;
    margin-top:3px;
  }

  .icon-key:before {
    content: "e635";
  }

  .icon-yonghuzhongxin:before {
    content: "e619";
  }
</style>
