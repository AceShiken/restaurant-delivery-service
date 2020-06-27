"# restaurant-delivery-service" 



**There are 3 microservices running on Eureka Server By Netflix**

*1. Eureka Server*

*2. Restaurant Service*

*3. Delivery Service*




**Steps to run**

*1. Run EurekaserverApplication from eureka server microservice.*

*2. Run DeliveryserviceApplication and RestaurantserviceApplication from delivery and restaurant microservices respectively.*



**Note :**

*1. Eureka server will run on port : 8761*

*2. Delivery Service on port : 8100*

*3. Restaurant Service on port : 8200*


>All of above are configurable in application.yml file respectively.




**Sample Requests And Responses**

*Getting menu card :*
```
Request : 
  curl --location --request GET 'localhost:8200/restaurant/order/getMenu'
Response: 
  [
      {
          "id": 756541,
          "name": "Roti",
          "cost": 12.0,
          "description": null
      },
      {
          "id": 950035,
          "name": "Rice",
          "cost": 30.0,
          "description": null
      },
      {
          "id": 703285,
          "name": "Naan",
          "cost": 16.0,
          "description": null
      },
      {
          "id": 332967,
          "name": "Butter Naan",
          "cost": 20.0,
          "description": null
      },
      {
          "id": 525409,
          "name": "Dal Makhani",
          "cost": 40.0,
          "description": null
      },
      {
          "id": 856611,
          "name": "Matar Paneer",
          "cost": 70.0,
          "description": null
      },
      {
          "id": 144868,
          "name": "Rajma",
          "cost": 50.0,
          "description": null
      },
      {
          "id": 456576,
          "name": "Chhole",
          "cost": 60.0,
          "description": null
      },
      {
          "id": 411629,
          "name": "Raita",
          "cost": 10.0,
          "description": null
      },
      {
          "id": 151296,
          "name": "Lassi",
          "cost": 40.0,
          "description": null
      }
  ]
```
  
*Placing an order:*
```
Request :
  curl --location --request POST 'localhost:8200/restaurant/placeOrder' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "orderList" : [
          {"name" : "Rice", "quantity" : 1},
          {"name" : "Chhole", "quantity" : 1},
          {"name" : "Lassi", "quantity" : 1}
      ]
  }'
Response :
  {
      "orderId": 647075,
      "orderStatus": "ACCEPTED"
  }
```

*Getting order details:*
```
Request :
  curl --location --request GET 'localhost:8200/restaurant/order/647075'
Response :
  {
      "orderTime": "2020-06-27T15:35:19.399+00:00",
      "estimateTimeInMinutes": 3,
      "deliveryTime": "2020-06-27T15:38:19.399+00:00",
      "orderList": [
          {
              "name": "Rice",
              "quantity": 1
          },
          {
              "name": "Chhole",
              "quantity": 1
          },
          {
              "name": "Lassi",
              "quantity": 1
          }
      ],
      "status": "DELIVERED",
      "payableAmount": 150.0
  }
```
  
*Get all available partners:*
```
Request :
  curl --location --request GET 'localhost:8100/delivery/getAllAvailablePartner'
Response :
  [
      {
          "id": 599116,
          "orderId": null,
          "name": "PartnerOne",
          "contact": 1234567899,
          "status": "IDLE"
      },
      {
          "id": 130098,
          "orderId": null,
          "name": "PartnerTwo",
          "contact": 2234567899,
          "status": "IDLE"
      },
      {
          "id": 286654,
          "orderId": null,
          "name": "PartnerThree",
          "contact": 3234567899,
          "status": "IDLE"
      },
      {
          "id": 903284,
          "orderId": null,
          "name": "PartnerFour",
          "contact": 4234567899,
          "status": "IDLE"
      },
      {
          "id": 775252,
          "orderId": null,
          "name": "PartnerFive",
          "contact": 5234567899,
          "status": "IDLE"
      },
      {
          "id": 232391,
          "orderId": null,
          "name": "PartnerSix",
          "contact": 6234567899,
          "status": "IDLE"
      },
      {
          "id": 244979,
          "orderId": null,
          "name": "PartnerSeven",
          "contact": 7234567899,
          "status": "IDLE"
      },
      {
          "id": 225580,
          "orderId": null,
          "name": "PartnerEight",
          "contact": 8234567899,
          "status": "IDLE"
      },
      {
          "id": 920275,
          "orderId": null,
          "name": "PartnerNine",
          "contact": 9234567899,
          "status": "IDLE"
      },
      {
          "id": 598402,
          "orderId": null,
          "name": "PartnerTen",
          "contact": 9934567899,
          "status": "IDLE"
      }
  ]
```

*Get partner status:*
```
Request :
  curl --location --request GET 'localhost:8100/delivery/partnerStatus/599116'
Response :
  {
      "status": "IDLE",
      "deliveryTimeLeft": 0
  }
```

*Get all active partners:*
```
Request :
  curl --location --request GET 'localhost:8200/restaurant/getPartners'
Response :
  {
      "partners": [
          {
              "partnerId": 599116,
              "orderId": 647075,
              "partnerStatus": "IDLE"
          }
      ]
  }
```
