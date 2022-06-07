<template>
<div style="font-size: 12px; line-height: 60px; display: flex">
    <div style="flex: 1; ">
        <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>
        <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px; margin-top: 15px" >
            <el-breadcrumb-item :to=" '/' ">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
<!--    https://img-blog.csdnimg.cn/ed4c96e20156458c992b916cfffd6866.png https://cdn.jsdelivr.net/gh/AXDecap/genshinurl/img/url.png-->
    <el-dropdown style="width:150px; height: 60px; cursor: pointer; text-align: right">
        <div style="display: inline-block;">
            <img :src="user.avatarUrl" alt="" style="width: 30px; border-radius: 50%; position: relative; top: 15px; right: 40px">
            <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
        </div>
        <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <router-link to="/password">修改密码</router-link>
            </el-dropdown-item>
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
               <router-link to="/person">个人信息</router-link>
            </el-dropdown-item>
            <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <span style="text-decoration: none" @click="logout">退出</span>
            </el-dropdown-item>
        </el-dropdown-menu>
    </el-dropdown>
</div>
</template>

<script>
    export default {
        name: "Header",
        props: { //接收Manage传过来的参数
            collapseBtnClass: String,
            user: Object

        },
        computed: {
            currentPathName() {
                return this.$store.state.currentPathName;　　//需要监听的数据
            }
        },
        data() {
            return {

            }
        },
        methods: {
            collapse() {
            // this.$parent.$parent.$parent.$parent.collapse()  // 通过4个 $parent 找到父组件，从而调用其折叠方法
                 this.$emit("asideCollapse")
            },
            logout() {
              this.$router.push("/login")
                this.$store.commit("logout")
                localStorage.removeItem("user")
                this.$message.success("退出成功")
            }
        },
        // watch: {  //监听路由变化
        //     currentPathName(newVal, oldVal) {
        //         console.log(newVal)
        //     }
        // }
    }
</script>

<style scoped>

</style>
