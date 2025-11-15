package com.utn.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductosApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductosApiRestApplication.class, args);
	}

    String consultaS =
            """
                    Datos ingresados : 
                    {
                      "nombre": "Nutella ",
                      "descripcion": "Frasco Nutella , 400gr",
                      "precio": 50000,
                      "stock": 10,
                      "categoria": "ALIMENTOS"
                    }
                    
                    Antes actualizar Stock
                    {
                      "nombre": "Notebook Hp",
                      "descripcion": "Ryzen 5, 12GB RAM",
                      "precio": 9500.00,
                      "stock": 10,
                      "categoria": "ELECTRONICA"
                    }  
                    Despues de Patch
                       {
                      "nombre": "Notebook Hp",
                      "descripcion": "Ryzen 5, 12GB RAM",
                      "precio": 9500.00,
                      "stock": 12,
                      "categoria": "ELECTRONICA"
                    }  
                    
                    
                    
                    {
                      "nombre": "Lenovo Legion 5",
                      "descripcion": "Ryzen 9, 32GB RAM",
                      "precio": 19500.00,
                      "stock": 13,
                      "categoria": "ELECTRONICA"
                    }   
                    
                    Antes de Actualizar
                    {
                      "nombre": "Bate Titanio",
                      "descripcion": "Bate 1,2 mt , 3kg ",
                      "precio": 1500.00,
                      "stock": 3,
                      "categoria": "DEPORTES"
                    }  
                     Despues de Actualizar
                     {
                      "nombre": "Bate Titanio (Aleacion)",
                      "descripcion": "Bate 1,21 mt , 3.2kg ",
                      "precio": 1700.00,
                      "stock": 2,
                      "categoria": "DEPORTES"
                    } 
                     
                     DELETE de Raqueta id = 6
                     {
                      "nombre": "Raqueta",
                      "descripcion": "Raqueta Tenis, Profesional",
                      "precio": 1400.00,
                      "stock": 2,
                      "categoria": "DEPORTES"
                    }
                     
                     {
                      "nombre": "Zapatillas Converse",
                      "descripcion": "Talle 40 , color = negro",
                      "precio": 3400.00,
                      "stock": 5,
                      "categoria": "ROPA"
                    }
                      
                      {
                      "nombre": "Pepinillos en Vinagre",
                      "descripcion": " contiene 10 unidades",
                      "precio": 400.00,
                      "stock": 4,
                      "categoria": "ALIMENTOS"
                    }
                    
                    Ingreso de Datos invalidos -> 400 
                         {
                      "nombre": "Pepinillos en Vinagre(negativo)",
                      "descripcion": " contiene 10 unidades",
                      "precio": -400.00,
                      "stock": -4,
                      "categoria": "ALIMENTOS"
                    }
            
            
                        
            
                    
                    
            """;




}
