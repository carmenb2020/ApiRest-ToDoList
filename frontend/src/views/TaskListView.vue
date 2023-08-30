<script setup>
import { useRoute } from 'vue-router'
import TaskListPriority from '../components/TaskListPriority.vue';

const	route = useRoute();
const	priority = route.params.priority;

</script>

<template>
	<TaskListPriority :priority="priority"/>
</template>