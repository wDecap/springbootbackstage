<template>
    <el-container style="min-height: 100vh">
        <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
            <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow" />
        </el-aside>

        <el-container>

            <el-header style=" border-bottom: 1px solid #ccc; ">
               <Header :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse" :user="user"/>
            </el-header>
            <el-main>
<!--当前页面的主路由会在router-view 里面显示-->
         <router-view @refreshUser="getUser" />
            </el-main>

        </el-container>
    </el-container>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
    // import request from "@/utils/request";可以用this.request
    export default {
        name: 'Home',
        data() {

            return {
                collapseBtnClass: 'el-icon-s-fold',
                isCollapse: false,
                sideWidth: 200,
                logoTextShow: true,
                user: {},

            }
        },
        created() {//调用后台方法
            //从后台获取最新User数据
            this.getUser()
        },

        components: {//引进组件
            Aside,
            Header
        },
        methods: {
            collapse() {  // 点击收缩按钮触发
                this.isCollapse = !this.isCollapse
                if (this.isCollapse) {  // 收缩
                    this.sideWidth = 64
                    this.collapseBtnClass = 'el-icon-s-unfold'
                    this.logoTextShow = false
                } else {   // 展开
                    this.sideWidth = 200
                    this.collapseBtnClass = 'el-icon-s-fold'
                    this.logoTextShow = true
                }
            },
            getUser(){
                // 先从浏览器缓存拿到username
                let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
                //然后再从后台获取User用户数据
                return  (this.request.get("/user/username/"+username)).then(res => {
                    //重新赋值后台最新User数据
                    this.user = res.data
                })
            },

        }


    }
</script>

