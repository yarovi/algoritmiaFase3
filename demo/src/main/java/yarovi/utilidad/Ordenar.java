package yarovi.utilidad;


import yarovi.entidad.Pedido;

public class Ordenar {

	/*
	 * se plantean metodos de ordenacion de forma generica
	 * */
	public <T> ListaEnlazada<T> metodoBurbuja(ListaEnlazada<T> lista) {

		for (int i = 1; i < lista.size(); i++) {

			for (int j = 0; j < lista.size() - i; j++) {

				switch (lista.get(i).getClass().getName()) {

				case "yarovi.entidad.Pedido":

					Pedido p = (Pedido) lista.get(j);
					if (p.getPedidoTotal().compareTo(((Pedido) lista.get(j + 1)).getPedidoTotal()) > 0) {
						T temp = lista.get(j);
						lista.modify(lista.get(j + 1),j);
						lista.modify(temp,j + 1);
					}

					break;
				case "":

					break;
				}
			}

		}

		return lista;
	}

	public <T> ListaEnlazada<T> metodoInserccionDirecta(ListaEnlazada<T> lista) {

		int i, j;
		T temp = null;
		for (i = 1; i < lista.size(); i++) {

			switch (lista.get(i).getClass().getName()) {

			case "yarovi.entidad.Pedido":

				Pedido p = (Pedido) lista.get(i);
				j = i - 1;
				while (p.getPedidoNombreVendedor().compareTo(((Pedido) lista.get(j + 1)).getPedidoNombreVendedor()) > 0 && j >= 0) {

					lista.modify( lista.get(j),j + 1);
					j--;
				}
				lista.modify(temp,j + 1);
				break;
			case "":

				break;
			}

		}

		return lista;
	}
}
