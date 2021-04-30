package com.ird.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DETALLE_PEDIDO")
public class LineaOrden {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ORDEN_FK", nullable = false)
	private Orden orden;

	@ManyToOne
	@JoinColumn(name = "PRODUCTO_FK", nullable = false)
	private Producto producto;

	@Column(name = "PRECIO", nullable = false)
	private Double precio;

	@Column(name = "CANTIDAD", nullable = false)
	private Double cantidad;

	@Column(name = "TOTAL_DETALLE", nullable = false)
	private Double totalDetalle;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaOrden other = (LineaOrden) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
