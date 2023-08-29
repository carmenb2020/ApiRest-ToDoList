<script setup>
import {ref, onBeforeMount} from 'vue'
import StatusSelector from './StatusSelector.vue'
import EditButton from './EditButton.vue'
import { format } from 'date-fns'

const props = defineProps (
	{
		task:
		{
			type: Object,
		}
	}
)

const currentStatus = ref();

onBeforeMount(() =>
{
	currentStatus.value = props.task.status;
	props.task.dueDate = format(new Date(props.task.dueDate), 'dd-MM-yyyy');
})


</script>

<template>
	<main class="task-container">
		<section class="status-selector">
			<StatusSelector :id="task.id" :status="task.status" @response="(data) => currentStatus = data"/>
		</section>
		<section class="task-info scrollbar">
			<h3 v-if="currentStatus" class="done">{{ task.title }}</h3>
			<h3 v-else>{{ task.title }}</h3>
			<p v-if="task.description">{{ task.description }}</p>
			<p>{{ task.dueDate }}</p>
			<p>{{ task.priority }}</p>
		</section>
		<section class="task-edit">
			<EditButton :id="task.id"/>
		</section>
	</main>
</template>

<style scoped>
	.task-container
	{
		display: flex;
    	align-items: center;
		justify-content: space-around;
    	background-color: white;
    	height: 7rem;
		border-radius: 0.8rem;
		margin: 1rem 0;
	}

	.status-selector, .task-edit
	{
		width: 15%;
	}
	.task-info
	{
		width: 60%;
    	height: 70%;
    	overflow-y: scroll;
		overscroll-behavior: none;
	}

	.status-selector
	{
		display: flex;
		justify-content: center;
	}

	.done
	{
		text-decoration: line-through;
	}

		/* total width */
	.scrollbar::-webkit-scrollbar 
	{
	    width: 6px;
	}

	/* background of the scrollbar except button or resizer */
	.scrollbar::-webkit-scrollbar-track 
	{
	    background-color: transparent; 
	}

	/* scrollbar itself */
	.scrollbar::-webkit-scrollbar-thumb 
	{
	    background-color: rgba(186, 186, 192, 0.5); 
	    border-radius: 16px;
	    border: 5px solid transparent; 
	}

	/* Set button (top and bottom of the scrollbar) */
	.scrollbar::-webkit-scrollbar-button 
	{
	    display: none;
	}

</style>