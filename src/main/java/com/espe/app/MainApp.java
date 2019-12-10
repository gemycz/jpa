package com.espe.app;

import java.util.Scanner;

import javax.persistence.EntityManager;

import com.espe.model.Cliente;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Cliente cliente;
		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (opcion != 5) {

			System.out.println("1. Crear Cliente");
			System.out.println("2. Buscar Cliente");
			System.out.println("3. Actualizar Cliente");
			System.out.println("4. Eliminar Cliente");
			System.out.println("5. Salir");
			System.out.println("Elegir una opcion: ");

			opcion = scanner.nextInt();

			switch (opcion) {
			case 1:
				System.out.println("Digite el nombre del Cliente");
				cliente = new Cliente();
				cliente.setIdCliente(null);
				scanner.nextLine();
				cliente.setNombreCli(scanner.nextLine());

				System.out.println("Digite el apellido del Cliente");
				cliente.setApellidoCli(scanner.nextLine());

				System.out.println("Digite la cedula del Cliente");
				cliente.setCedulaCli(scanner.nextLine());

				entity.getTransaction().begin();
				entity.persist(cliente);
				entity.getTransaction().commit();
				break;
			case 2:
				System.out.println("Digite el id del Cliente");
				cliente = new Cliente();
				cliente = entity.find(Cliente.class, scanner.nextInt());

				if (cliente != null) {
					System.out.println(cliente);
					System.out.println();

				} else {
					System.out.println("El Cliente no existe");

				}

				break;
			case 3:
				System.out.println("Digite el id del Cliente");
				cliente = new Cliente();
				cliente = entity.find(Cliente.class, scanner.nextInt());
				
				if (cliente != null) {
					
					System.out.println("Digite el nombre del Cliente");
					//cliente = new Cliente();
					//cliente.setIdCliente(null);
					scanner.nextLine();
					cliente.setNombreCli(scanner.nextLine());

					System.out.println("Digite el apellido del Cliente");
					cliente.setApellidoCli(scanner.nextLine());

					System.out.println("Digite la cedula del Cliente");
					cliente.setCedulaCli(scanner.nextLine());
					
					
					entity.getTransaction().begin();
					entity.merge(cliente);
					entity.getTransaction().commit();
					
					System.out.println("Cliente Actualizado..");
					System.out.println();
				}else {
					System.out.println("El Cliente no existe");

				}
				
				break;
			case 4:
				System.out.println("Digite el id del Cliente");
				cliente = new Cliente();
				cliente = entity.find(Cliente.class, scanner.nextInt());
				if (cliente != null) {
					System.out.println(cliente);
					entity.getTransaction().begin();
					entity.remove(cliente);
					entity.getTransaction().commit();
					
					System.out.println("Cliente Eliminado..");
					System.out.println();

				} else {
					System.out.println("El Cliente no existe");

				}

				break;
			case 5:
				entity.close();
				JPAUtil.shutdown();
				break;
			default:
					System.out.println("Opcion no valida");
			}
		}
	}

}
