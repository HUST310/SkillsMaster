
new Vue ({
    el: "#name",
    mounted(){
        this.getUserName();
    },
    data(){
        return {
            name:'加盟皇马'
        }
    },
    methods: {
        getUserName(){
            axios({
                method:"get",
                url:'/getUserName',
            }).then(resp=>{
                this.name=resp.data.name;
                $("#userName").text(this.name);
            });


        },
    }
});