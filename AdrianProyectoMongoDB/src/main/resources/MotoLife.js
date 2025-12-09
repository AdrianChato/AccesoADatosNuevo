use('MotoLife')
db.Motos.drop()
db.Motos.createIndex(
  { num_bas: 1 },
  { unique: true }
);
db.Motos.insertMany([
  {
    "num_bas": 1,
    "cc": 599.5,
    "cv": 85,
    "motor": "GASOLINA",
    "modelo": "Yamaha MT-07",
    "enVenta": true,
    "propietarios": [
      {"nombre": "Carlos Pérez", "dni": "12345678A", "direccion": "Calle Luna 45, Madrid", "telefono": 612345678}
    ],
    "especificaciones": {
      "anio_matriculacion": 2019,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": false
    }
  },
  {
    "num_bas": 2,
    "cc": 749.3,
    "cv": 110,
    "motor": "GASOLINA",
    "modelo": "Honda CB750",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Lucía Serrano", "dni": "11223344B", "direccion": "Av. Europa 11, Sevilla", "telefono": 623445667},
      {"nombre": "Miguel Ruiz", "dni": "99887766C", "direccion": "Calle Jardin 21, Málaga", "telefono": 655778899}
    ],
    "especificaciones": {
      "anio_matriculacion": 2017,
      "propietarios_totales": 2,
      "revisiones": true,
      "modificaciones": true
    }
  },
  {
    "num_bas": 3,
    "cc": 999.9,
    "cv": 150,
    "motor": "GASOLINA",
    "modelo": "Kawasaki Z1000",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2021,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 4,
    "cc": 450.7,
    "cv": 52,
    "motor": "GASOLINA",
    "modelo": "Suzuki DR-Z400",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Javier Ortega", "dni": "44556677D", "direccion": "Calle Norte 9, Zaragoza", "telefono": 644112233}
    ],
    "especificaciones": {
      "anio_matriculacion": 2016,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": true
    }
  },
  {
    "num_bas": 5,
    "cc": 124.4,
    "cv": 15,
    "motor": "GASOLINA",
    "modelo": "KTM Duke 125",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2023,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 6,
    "cc": 850.2,
    "cv": 95,
    "motor": "GASOLINA",
    "modelo": "BMW F850GS",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Ana Vidal", "dni": "22334455E", "direccion": "Calle Sur 17, Bilbao", "telefono": 611229944}
    ],
    "especificaciones": {
      "anio_matriculacion": 2018,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": false
    }
  },
  {
    "num_bas": 7,
    "cc": 320.8,
    "cv": 42,
    "motor": "ELECTRICA",
    "modelo": "Yamaha R3",
    "enVenta": true,
    "propietarios": [
      {"nombre": "David Esteban", "dni": "55667788F", "direccion": "Calle Verde 3, Murcia", "telefono": 677445522}
    ],
    "especificaciones": {
      "anio_matriculacion": 2020,
      "propietarios_totales": 1,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 8,
    "cc": 1198.6,
    "cv": 180,
    "motor": "GASOLINA",
    "modelo": "Ducati Panigale 1199",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Pablo Gutiérrez", "dni": "66778899G", "direccion": "Calle Sol 8, Barcelona", "telefono": 699887766}
    ],
    "especificaciones": {
      "anio_matriculacion": 2015,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": true
    }
  },
  {
    "num_bas": 9,
    "cc": 649.4,
    "cv": 68,
    "motor": "GASOLINA",
    "modelo": "Kawasaki Versys 650",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2022,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 10,
    "cc": 399.1,
    "cv": 45,
    "motor": "GASOLINA",
    "modelo": "Honda CBR400R",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Eva Delgado", "dni": "77889900H", "direccion": "Av. Mar 10, Alicante", "telefono": 644558877}
    ],
    "especificaciones": {
      "anio_matriculacion": 2019,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": false
    }
  },
  {
    "num_bas": 11,
    "cc": 278.9,
    "cv": 26,
    "motor": "GASOLINA",
    "modelo": "Honda Forza 300",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2023,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 12,
    "cc": 500.5,
    "cv": 47,
    "motor": "GASOLINA",
    "modelo": "Kawasaki Ninja 500",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Roberto López", "dni": "11225566I", "direccion": "Calle Bosque 14, León", "telefono": 611778899}
    ],
    "especificaciones": {
      "anio_matriculacion": 2018,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": true
    }
  },
  {
    "num_bas": 13,
    "cc": 300.3,
    "cv": 34,
    "motor": "GASOLINA",
    "modelo": "KTM RC 390",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2021,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 14,
    "cc": 650.7,
    "cv": 70,
    "motor": "ELECTRICA",
    "modelo": "Royal Enfield Interceptor 650",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Antonio Ramos", "dni": "33445566J", "direccion": "Calle Lago 6, Córdoba", "telefono": 622334455}
    ],
    "especificaciones": {
      "anio_matriculacion": 2018,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": false
    }
  },
  {
    "num_bas": 15,
    "cc": 783.2,
    "cv": 54,
    "motor": "ELECTRICA",
    "modelo": "BMW R80",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Santiago Molina", "dni": "99334455K", "direccion": "Calle Alta 12, Santander", "telefono": 655112233}
    ],
    "especificaciones": {
      "anio_matriculacion": 1989,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": true
    }
  },
  {
    "num_bas": 16,
    "cc": 223.6,
    "cv": 18,
    "motor": "GASOLINA",
    "modelo": "Honda CBF 250",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2020,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 17,
    "cc": 1099.4,
    "cv": 160,
    "motor": "ELECTRICA",
    "modelo": "Aprilia Tuono 1100",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Mario Caballero", "dni": "22119933L", "direccion": "Calle Azul 7, Valencia", "telefono": 677889900}
    ],
    "especificaciones": {
      "anio_matriculacion": 2016,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": true
    }
  },
  {
    "num_bas": 18,
    "cc": 451.8,
    "cv": 50,
    "motor": "GASOLINA",
    "modelo": "Suzuki GSX450",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2022,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 19,
    "cc": 159.9,
    "cv": 17,
    "motor": "GASOLINA",
    "modelo": "Yamaha XTZ 150",
    "enVenta": true,
    "propietarios": [],
    "especificaciones": {
      "anio_matriculacion": 2023,
      "propietarios_totales": 0,
      "revisiones": false,
      "modificaciones": false
    }
  },
  {
    "num_bas": 20,
    "cc": 1299.7,
    "cv": 200,
    "motor": "GASOLINA",
    "modelo": "KTM 1290 Super Duke R",
    "enVenta": false,
    "propietarios": [
      {"nombre": "Jordi Esteve", "dni": "77884422M", "direccion": "Av. Libertad 14, Girona", "telefono": 655441122}
    ],
    "especificaciones": {
      "anio_matriculacion": 2020,
      "propietarios_totales": 1,
      "revisiones": true,
      "modificaciones": true
    }
  }
]

)