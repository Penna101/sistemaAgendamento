<script>
export default {
  data: () => ({loading: false, extrato: null, dataConsulta: null}),
  methods: {
    load() {
      this.loading = true;
      fetch("http://localhost:8080/agendamento/extrato-total")
        .then(res => {
          if (res) {
            return res.json()
          } else alert('Sem extrato')
        })
        .then(data => {
          this.extrato = data;
          this.loading = false;
          this.dataConsulta = new Date()
        })
    }
  }
}
</script>

<template>
  <v-sheet width="30%" class="mx-auto mt-5 mb-5">
    <v-card
      variant="tonal"
      title="Extrato de Agendamentos"
    >
      <v-card-text v-if="extrato != null">
        R${{ extrato }}
      </v-card-text>
      <v-card-text v-if="dataConsulta != null">
        Atualizado em: {{ dataConsulta.toLocaleString() }}
      </v-card-text>
      <v-card-actions>
        <v-btn
          :loading="loading"
          @click="load"
        >Consultar
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-sheet>
</template>

<style scoped lang="sass">

</style>
