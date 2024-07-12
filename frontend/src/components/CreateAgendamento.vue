<script>
import {tr} from "vuetify/locale";

let now = new Date();
now.setHours(0, 0, 0, 0);
export default {
  data: () => ({
    loading: false,
    showAlertOk: false,
    showAlertError: false,
    errorText: 'Ocorreu um erro ao salvar',
    contaOrigem: '',
    contaOrigemRules: [
      value => {
        if (value?.match(/[a-zA-z]/gi) || value?.length !== 10) {
          return 'Contas devem ter exatamente 10 dígitos'
        }
        return true;
      }
    ],
    contaDestino: '',
    valor: null,
    valorRules: [
      value => {
        if (value == null || value < 0) {
          return 'O valor de transferência não pode ser negativo'
        }
        return true;
      }
    ],
    dataAgendamento: null,
    dataAgendamentoRules: [
      value => {
        if (value == null) {
          return 'O preenchimento é obrigatório'
        }
        let date = new Date(value);
        date.setHours(date.getHours() + 3)
        if (date < now) {
          return 'A data não pode ser anterior ao dia atual'
        }
        return true;
      }
    ]
  }),
  methods: {
    async createAgendamento(event) {
      this.loading = true;
      this.onCloseAlert()
      let results = await event;
      if (!results.valid) {
        this.loading = false;
        return;
      }
      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          contaOrigem: this.contaOrigem,
          contaDestino: this.contaDestino,
          valor: this.valor,
          dataAgendamento: this.dataAgendamento
        })
      };
      fetch("http://localhost:8080/agendamento", requestOptions)
        .then(response => {
          if (response.ok) {
            this.showAlertOk = true
            this.limparForm()
          } else {
            this.showAlertError = true;
          }
          return response.json()
        })
        .then(data => {
          if (data.message) {
            this.errorText = data.message;
          }
          this.loading = false
        });
    },
    limparForm() {
      this.$refs.form.reset()
    },
    onCloseAlert() {
      this.showAlertError = false
      this.showAlertOk = false
      this.errorText = 'Ocorreu um erro ao salvar'
    }
  }
}
</script>

<template>
  <v-sheet width="70%" class="mx-auto mb-5 mt-5">
    <v-sheet class="ml-2">
        <v-alert
          closable
          v-model="showAlertOk"
          class="mb-2"
          title="Sucesso"
          text="O agendamento foi salvo"
          type="success"
          @click:close="onCloseAlert"
        ></v-alert>
      <v-alert
        closable
        v-model="showAlertError"
        class="mb-2"
        title="Erro"
        :text="errorText"
        type="error"
        @click:close="onCloseAlert"
      ></v-alert>

      <v-form @submit.prevent="createAgendamento" ref="form">

        <v-text-field
          v-model="contaOrigem"
          label="Conta de Origem"
          :rules="contaOrigemRules"
        ></v-text-field>
        <v-text-field
          v-model="contaDestino"
          label="Conta de Destino"
          :rules="contaOrigemRules"
        ></v-text-field>
        <v-text-field
          outlined
          dense
          v-model="valor"
          type="number"
          hide-spin-buttons
          prefix="R$"
          label="Valor"
          :rules="valorRules"
        >
        </v-text-field>
        <v-text-field
          type="date"
          v-model="dataAgendamento"
          :rules="dataAgendamentoRules"
        ></v-text-field>
        <v-btn :loading="loading" class="mt-2" type="submit" block>Agendar</v-btn>
      </v-form>
    </v-sheet>

  </v-sheet>
</template>

<style scoped lang="sass">

</style>
