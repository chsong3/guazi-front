<template>

</template>

<script>
    export default {
        name: 'Blank.vue',
        mounted() {
            this.$router.push(this.$route.query.path)
        }
    };
</script>

<style scoped>

</style>
