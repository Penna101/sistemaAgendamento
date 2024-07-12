<template>
  <v-app>
    <v-main>
      <v-card>
        <v-tabs
          v-model="tab"
          align-tabs="center"
          color="deep-purple-accent-4"
        >
          <v-tab value="0">Agendar</v-tab>
          <v-tab value="1">Agendamentos</v-tab>
          <v-tab value="2">Extrato</v-tab>
        </v-tabs>

        <v-tabs-window v-model="tab">
          <v-tabs-window-item value="0">
            <create-agendamento></create-agendamento>
          </v-tabs-window-item>
          <v-tabs-window-item value="1" >
            <agendamentos></agendamentos>
          </v-tabs-window-item>
          <v-tabs-window-item value="2">
            <extrato></extrato>
          </v-tabs-window-item>
        </v-tabs-window>

      </v-card>
    </v-main>

<!--    <AppFooter />-->
  </v-app>
</template>

<script setup>
  //
import Agendamentos from "@/components/Agendamentos.vue";
  import CreateAgendamento from "@/components/CreateAgendamento.vue";
  import Extrato from "@/components/Extrato.vue";
</script>

<script>
  import Agendamentos from "@/components/Agendamentos.vue";

  export default {
    data: () => ({
      tab: null,
      agendamentosComponent: Agendamentos
    }),
    methods: {
      loadAgendamentos() {
        this.agendamentosComponent.methods.load();
      }
    }
  }
</script>
