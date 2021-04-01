
var customerApi = Vue.resource("/customer{/id}")

Vue.component('customer-row', {
    props: ['customer'],
    template: '<div><i>({{ customer.id }})</i> {{ customer.name }} --- {{ customer.notes }}</div>'
})

Vue.component('customers-list', {
    props: ['customers'],
    template:
        '<div>' +
        '<customer-row v-for="customer in customers" :key="customer.id" :customer="customer" />' +
        '</div>',
    created: function () {
        customerApi.get().then(result =>
            result.json().then(data=>
                data.forEach(customer => this.customers.push(customer))
            )
        )
    }
})

var app = new Vue({
    el: '#app',
    template: '<customers-list :customers="customers" />',
    data: {
        customers: []
    }
});