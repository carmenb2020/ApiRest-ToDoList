<script setup>
import GetTasks from "../services/ApiConnection"

	const	props = defineProps (
		{
			id:
			{
				type: Number,
			},
			status:
			{
				type: Boolean,
			}
		}
	)


	const	getTasks = new GetTasks();
	const	emit = defineEmits(['response']);
	
	const	ftStatusChange = async() =>
	{
		const task = await getTasks.getTaskById(props.id);
		task.data.status = !task.data.status;
		await getTasks.updateTask(props.id, task.data);
		emit('response', task.data.status);
	}

</script>

<template>
	<input v-if="props.status" type="checkbox" @change="ftStatusChange()" checked>
	<input v-else type="checkbox" @change="ftStatusChange()">
</template>