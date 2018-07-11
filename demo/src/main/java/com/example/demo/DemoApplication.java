package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages= {
		"yarovi.controller",
		"yarovi.entidad",
		"yarovi.utilidad",
		"yarovi.DAO",
		"yarovi.Service"
})
public class DemoApplication //implements CommandLineRunner{
{
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Autowired
//	private AlmacenDAO almacendao;
//	@Override
//	public void run(String... arg0) throws Exception {
//		// TODO Auto-generated method stub
//
//			Nodo<Almacen> a = new Nodo<>();
//			a.dato.setAlmacenId(1);
//			a.dato.setAlmacenNombre("AQP001");
//			a.dato.setAlmacenEstado(true);
//			almacendao.insertAlmacen(a);
//			Nodo<Almacen> b = new Nodo<>();
//			a.dato.setAlmacenId(2);
//			a.dato.setAlmacenNombre("AQP002");
//			a.dato.setAlmacenEstado(true);
//			almacendao.insertAlmacen(b);
//
//	}
}
