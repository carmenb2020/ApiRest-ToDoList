<script setup>
import ApiConnection from '../services/ApiConnection'
import PriorityDropdown from '../components/PriorityDropdown.vue'
import CompleteButton from '../components/CompleteButton.vue'
import Calendar from "./Calendar.vue";
import CloseButton from '../components/CloseButton.vue'
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';

const props = defineProps({
  title: {
    type: String,
  }
});

const id = ref()
const router = useRouter();

const date = new Date;
const newTask = ref({
  id: null,
  title: '',
  description: '',
  dueDate: '',
  priority: '',
  status: '',
  category: ''
})

const addTask = new ApiConnection();

const isAllFieldsFilled = computed(() => {
  return (
    newTask.value.title.trim() !== '' &&
    newTask.value.dueDate.trim() !== '' &&
    newTask.value.priority.trim() !== ''
  );
});

const submit = async () => {
  if (isAllFieldsFilled.value) {
    await addTask.addTask(newTask.value);
	router.push({name: 'TaskList', params: {priority: newTask.value.priority, modal: true, action: "added"}});
  } else {
    alert('Please fill in all required fields.')
  }
}

</script>

<template>
  <main class="main-container">
    <div id="top">
      <CloseButton path="/"/>

      <h2 class="add-task-title">Add Task</h2>

      <h4>Task</h4>
      <input v-model="newTask.title" id="task-text" type="text" placeholder="Enter Task" required="true"/>

      <h4>Description (Optional)</h4>
      <input v-model="newTask.description" id="description-text" type="text" placeholder="Enter Description" />

      <h4>Category (Optional)</h4>
      <input v-model="newTask.category" id="category-text" type="text" placeholder="Enter Category" />
      
    </div>
    <div>
      <CompleteButton @click="submit()" :fill="isAllFieldsFilled ? '#FF9E13' : '#565656'" />
    </div>
    <div id="bottom">
      <div class="due-date-container">
        <div class="due-date-content">
          <svg
          xmlns="http://www.w3.org/2000/svg"
          width="20"
          height="23"
          viewBox="0 0 20 23"
          fill="none"
          class="calendar-icon"
        >
          <path
            d="M17.8571 2.85714H15.7143V0.535714C15.7143 0.241071 15.4732 0 15.1786 0H13.3929C13.0982 0 12.8571 0.241071 12.8571 0.535714V2.85714H7.14286V0.535714C7.14286 0.241071 6.90179 0 6.60714 0H4.82143C4.52679 0 4.28571 0.241071 4.28571 0.535714V2.85714H2.14286C0.959821 2.85714 0 3.81696 0 5V20.7143C0 21.8973 0.959821 22.8571 2.14286 22.8571H17.8571C19.0402 22.8571 20 21.8973 20 20.7143V5C20 3.81696 19.0402 2.85714 17.8571 2.85714ZM17.5893 20.7143H2.41071C2.26339 20.7143 2.14286 20.5938 2.14286 20.4464V7.14286H17.8571V20.4464C17.8571 20.5938 17.7366 20.7143 17.5893 20.7143Z"
            fill="black"
          />
        </svg>
          <h3 id="dueDateTitle">Due Date</h3>
          <Calendar class="calendar" @date="(date) => (newTask.dueDate = date)"/>
        </div>
      </div>

      <div class="priority-container">
        <div class="priority-content">
          <svg
          xmlns="http://www.w3.org/2000/svg"
          width="5"
          height="16"
          viewBox="0 0 5 16"
          fill="none"
          class="priority-icon"
        >
          <path
            d="M5 13.5C5 14.8785 3.8785 16 2.5 16C1.1215 16 0 14.8785 0 13.5C0 12.1215 1.1215 11 2.5 11C3.8785 11 5 12.1215 5 13.5ZM0.289375 0.787469L0.714375 9.28747C0.734344 9.68663 1.06378 10 1.46344 10H3.53656C3.93622 10 4.26566 9.68663 4.28562 9.28747L4.71063 0.787469C4.73203 0.359062 4.3905 0 3.96156 0H1.03844C0.6095 0 0.267969 0.359062 0.289375 0.787469Z"
            fill="black"
          />
        </svg>
          <h3 id="priority-content-title">Priority</h3>
        </div>
        <PriorityDropdown class="select-dropdown" @priority="(priority) => newTask.priority = priority"/>
      </div>
    </div> 
  </main>
</template>

<style scoped>
.main-container {
  overflow: hidden;
  background: var(--vt-c-white-soft);
  font-size: 62.5%;
  height: 91vh;
}

#top {
  background: #dd4b39;
  padding: 0.4rem 2rem;
  border-radius: 0.6rem;
}

.add-task-title {
  color: var(--vt-c-white);
  margin: 0;
  text-align: center;
  font-size:1.5rem;
}

h3 {
  font-weight: 700;
  font-size:1.2rem;
}

h4 {
  color: #FF9E13;
  margin: 1rem 0;
  font-size:1.2rem;
}

input {
  background-color: #dd4b39;
  border: none;
  border-bottom: 0.1rem solid #FF9E13;
  padding: 0.5rem;
  color: #ffffff;
  font-size: 1.2rem;
  width: 40vmin;
}

input:focus {
  border: 0.2rem solid white;
  outline: 0.2rem solid #FF9E13;
  border-color: white;
  color: var(--vt-c-white);
}

#task-text::placeholder {
  color: #ffffff;
  opacity: 0.5;
}

#description-text::placeholder {
  color: #fff;
  opacity: 0.5;
}

#category-text::placeholder {
  color: #ffffff;
  opacity: 0.5;
}

.due-date-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.calendar-icon {
  fill: white;
  width: 1.5rem;
  height: 1.5rem;
}

.due-date-content {
  display:flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.calendar {
  margin-top: 1rem;
  margin-bottom: 0.5rem;
}

p {
  padding: 0.5rem 0 0 7rem; 
  color: #dd4b39;
  font-style: italic;
}

.priority-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.priority-icon {
  fill: black;
  width: 1.5rem;
  height: 1.5rem;
}

#priority-content-title{
  margin-bottom: 1.2rem;
}
.priority-content {
  display: flex;
  flex-direction: row;
}

#bottom {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

@media (max-width: 648px) {
  #bottom{
    display:flex;
    flex-direction: column;
    align-items: flex-start;
    gap:2rem;
    margin-left: 1rem;
  }
}
</style>