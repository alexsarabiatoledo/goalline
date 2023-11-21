<template>
  <div>
    <button  v-if="virtualAssistantVisible" @click="openChat" class="btn btn-sm floating">
      <i class="bi bi-robot fab-icon"></i>
    </button>
  </div>
  <div v-if="deployChat" class="virtualassistant-container">
    <img src="https://e7.pngegg.com/pngimages/259/306/png-clipart-robot-robot.png" alt="virtualassistant Image" class="virtualassistant-image" />
    <div class="chatbox">
      <!-- Aquí se mostrarán los mensajes de la conversación -->
      <div class="message" v-for="(message, index) in messages" :key="index">
        {{ message.text }}
      </div>
    </div>
    <div class="input-box">
      <input v-model="userInput" @keyup.enter="sendMessage" placeholder="Escribe un mensaje..." />
      <button @click="sendMessage">Enviar</button>
    </div>
  </div>
</template>
  
  <script>
  import { mapState } from 'vuex';
  import axios from 'axios';

  export default {
    computed: {
    ...mapState({
      virtualAssistantVisible: state => state.virtualAssistantVisible,
      }),
    },
    name: 'VirtualAssistant',
    data() {
      return {
        messages: [],
        userInput: '',
        deployChat: false
      };
    },
    mounted() {
    this.messages.push({ text: '¡Hola! Soy el asistente virtual. Puedes hacerme preguntas como:' });
    this.messages.push({ text: '- "¿Qué es una SSD?"' });
    this.messages.push({ text: '¡Estoy aquí para ayudarte!' });
  },
    methods: {
      openChat(){
        this.deployChat = !this.deployChat;
        console.log(this.deployChat);
      },
      async sendMessage() {
        if (this.userInput) {
          this.messages.push({ text: this.userInput, user: 'user' });
          try {
           const response = await axios.post('http://localhost:8081/questions/send', { message: this.userInput });
           const responseText = response.data.data;
          // Verifica si la respuesta es un JSON o texto
          if (typeof responseText === 'string') {
            this.messages.push({ text: responseText, user: 'bot' });
          }
         } catch (error) {
          console.error(error);
         }
          this.userInput = '';
        }
      },
    },
    
  };
  </script>
  
  <style scoped>
  .virtualassistant-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #f0f0f0;
    border: 1px solid #eb7c21;
    border-radius: 5px;
    padding: 10px;
    width: 350px;
    position: fixed;
    bottom: 20px;
    right: 20px;
  }
  
  .virtualassistant-image {
    width: 90px;
    height: 90px;
    object-fit: cover;
    border-radius: 25%;
  }
  
  .chatbox {
    height: 250px;
    width: 100%;
    overflow-y: auto;
    border: 1px solid #f38116;
    margin-top: 30px;
    padding: 20px;
  }
  
  .message {
    background-color: #fff;
    border: 2px solid #ccc;
    border-radius: 10px;
    padding: 5px;
    margin: 5px;
  }
  
  .input-box {
    display: flex;
    align-items: center;
    margin-top: 20px;
  }
  
  input {
    flex: 1;
    padding: 6px;
    border: 1px solid #ccc;
    border-radius: 5px;
    width: 260px;
  }
  
  button {
    background-color: #15dd61;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 6px 12px;
    margin-left: 6px;
    cursor: pointer;
  }

  .floating {
 position: fixed;
 width: 60px;
 height: 60px;
 bottom: 40px;
 right: 40px;
 background-color: #25d366;
 color: #fff;
 border-radius: 50px;
 text-align: center;
 font-size: 30px;
 box-shadow: 2px 2px 3px #999;
 z-index: 100;
}

.fab-icon {
 margin-top: 16px;
}
  </style>