<script>

export default {
  data() {
    return {
      headers: [
        {title: "Conta de Origem", key: "contaOrigem", align: "center"},
        {title: "Conta de Destino", key: "contaDestino", align: "center"},
        {title: "Valor", key: "valor", align: "end"},
        {title: "Taxa", key: "taxa", align: "end"},
        {title: "Data de Agendamento", key: "dataAgendamento", align: "end"},
      ],
      agendamentos: [],
      loading: false,
    }
  },
  methods: {
    async load() {
      this.loading = true
      await fetch('http://localhost:8080/agendamento')
        .then(res => res.json())
        .then(data => {
          this.agendamentos = data;
          this.loading = false;
        })
    }
  }
}

</script>

<template>

  <v-data-table
    class="mt-2"
    @update:options="load"
    :loading="loading"
    :items="agendamentos"
    :headers="headers"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>
          Todos os agendamentos
        </v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-btn
          variant="outlined"
          color="primary"
          @click="load"
        >
          Pesquisar</v-btn>
      </v-toolbar>
    </template>
    <template v-slot:item.valor="{value}">
      R$ {{ value }}
    </template>
    <template v-slot:item.taxa="{value}">
      {{ value }}%
    </template>
    <template v-slot:item.dataAgendamento="{value}">
      {{ new Date(value).toDateString() }}
    </template>
  </v-data-table>

</template>

<style scoped lang="sass">

</style>
