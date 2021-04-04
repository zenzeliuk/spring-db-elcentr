
function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var customerApi = Vue.resource("/customer{/id}")

Vue.component('customer-form', {
    props: ['customers', 'customerAttr'],
    data: function () {
        return {
            name: '',
            notes: '',
            id: ''
        }
    },
    watch: {
        customerAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.notes = newVal.notes;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Name customer" v-model="name"/>' +
            '<input type="text" placeholder="Notes customer" v-model="notes"/>' +
            '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var customerCreate = {name: this.name, notes: this.notes};
            var customerUpdate = {id: this.id, name: this.name, notes: this.notes};

            if (this.id){
                customerApi.update({}, customerUpdate).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.customers, data.id)
                        this.customers.splice(index, 1, data);
                        this.id = '';
                        this.name = '';
                        this.notes = '';
                    })
                )
            } else {
                customerApi.save({}, customerCreate).then(result =>
                    result.json().then(data => {
                        this.customers.push(data);
                        this.name = '';
                        this.notes = '';
                    })
                )
            }
        }
    }
});

Vue.component('customer-row', {
    props: ['customer', 'editMethod', "customers"],
    template:
        '<div>' +
            '<i>({{ customer.id }})</i> {{ customer.name }} --- {{ customer.notes }}' +
            '<span style="position: absolute; right: 0">' +
                '<input type="button" value="Edit" @click="edit" />' +
                '<input type="button" value="X" @click="del" />' +
            '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.customer);
        },
        del: function () {
            customerApi.remove({id: this.customer.id}).then(result => {
                if (result.ok) {
                    this.customers.splice(this.customers.indexOf(this.customer), 1)

                }
            })
        }
    }
})

Vue.component('customers-list', {
    props: ['customers'],
    data: function (){
        return {
            customer: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
            '<customer-form :customers="customers" :customerAttr="customer" />' +
            '<customer-row v-for="customer in customers" :key="customer.id" :customer="customer" :editMethod="editMethod" :customers="customers"/>' +
        '</div>',
    created: function () {
        customerApi.get().then(result =>
            result.json().then(data =>
                data.forEach(customer => this.customers.push(customer))
            )
        )
    },
    methods: {
        editMethod: function (customer) {
            this.customer = customer;
        }
    }
})

var app = new Vue({
    el: '#app',
    template: '<customers-list :customers="customers" />',
    data: {
        customers: []
    }
});