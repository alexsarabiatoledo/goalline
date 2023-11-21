import { createApp } from 'vue';
import App from './App.vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { createRouter, createWebHashHistory } from 'vue-router';
import AddStudent from '../src/views/AddStudent'
import AddTeacher from '../src/views/AddTeacher'
import MainView from '../src/views/MainView'
import LoginView from '../src/views/LoginView'
import ClassRegistration from '../src/views/ClassRegistration'
import NewClass from '../src/views/NewClass'
import StudentAttendance from '../src/views/StudentAttendance'
import store from './store';
import StatsStudent from '../src/views/StatsStudent';
import SeeStudent from '../src/views/SeeStudent';
import ChooseStudent from '../src/views/ChooseStudent.vue'
import CreateTeam from '../src/views/CreateTeam';
import MyTeams from '../src/views/MyTeams';
import AddParent from '../src/views/AddParent'
import SelectStudent from '../src/views/SelectStudent'
import NewTournamentSelectTeams from '../src/views/NewTournamentSelectTeams'
import NewTournamentGenerateMatches from '../src/views/NewTournamentGenerateMatches'
import Testeado from '../src/views/Testeado'


const routes = [
  //Administrador
  {
    path: '/home',
    component: MainView,
  },
  {
    path: '/testeado',
    component: Testeado,
  },
  {
    path: '/newTournamentSelectTeams',
    component: NewTournamentSelectTeams,
  },
  {
    path: '/newTournamentGenerateMatches',
    name: 'NewTournamentGenerateMatches',
    component: NewTournamentGenerateMatches,
  },
  {
    path: '/classes',
    component: ClassRegistration,
  },
  {
    path: '/add_student',
    component: AddStudent,
  },
  {
    path: '/add_teacher',
    component: AddTeacher,
  },
  {
    path: '/select_student',
    component: SelectStudent
  },
  {
    path: '/newclass',
    component: NewClass,
  },
  {
    path: '/attendance/:id',
    name: 'attendance',
    component: StudentAttendance,
  },
  {
    path: '/',
    component: LoginView,
  },
  {
    path: '/newteam',
    component: CreateTeam,
  },
  {
    path: '/myteams',
    component: MyTeams,
  },
  {
    path: '/student_stats',
    component: StatsStudent,
  },
  {
   path: '/see_student/:id',
   component: SeeStudent,
  },
  {
  path: '/choose_student',
  component: ChooseStudent,
  },
  {
    path: '/add_parent',
    component: AddParent,
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

const app = createApp(App);

app.use(store);
app.use(router);

app.mount('#app');