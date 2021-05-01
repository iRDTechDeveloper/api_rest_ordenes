insert into productos (categoria, nombre, precio) values ('Categoria 1', 'Producto 1', 1000);
insert into productos (categoria, nombre, precio) values ('Categoria 2', 'Producto 2', 2000);
insert into productos (categoria, nombre, precio) values ('Categoria 3', 'Producto 3', 3000);
insert into productos (categoria, nombre, precio) values ('Categoria 4', 'Producto 4', 4000);
insert into productos (categoria, nombre, precio) values ('Categoria 5', 'Producto 5', 5000);
insert into productos (categoria, nombre, precio) values ('Categoria 6', 'Producto 6', 6000);
insert into productos (categoria, nombre, precio) values ('Categoria 7', 'Producto 7', 7000);
insert into productos (categoria, nombre, precio) values ('Categoria 8', 'Producto 8', 8000);
insert into productos (categoria, nombre, precio) values ('Categoria 9', 'Producto 9', 9000);
insert into productos (categoria, nombre, precio) values ('Categoria 10', 'Producto 10', 10000);

insert into pedidos(fecha_registro, total_pedido) values (now(), 1500);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (1, 1, 1000, 1, 1000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (1, 2, 2000, 1, 2000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (1, 3, 3000, 1, 3000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (1, 4, 4000, 1, 4000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (1, 5, 5000, 1, 5000);

insert into pedidos(fecha_registro, total_pedido) values (now(), 4500);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (2, 6, 6000, 1, 6000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (2, 7, 7000, 1, 7000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (2, 8, 8000, 1, 8000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (2, 9, 9000, 1, 9000);
insert into detalle_pedido(orden_fk, producto_fk, precio, cantidad, total_detalle) values (2, 10, 10000, 1, 10000);
