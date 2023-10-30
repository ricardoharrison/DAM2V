create or replace type veterinario as object (
    vet_id number,
    nombre varchar2(50),
    direccion varchar2(50)
);
/

create or replace type mascota as object (
    mas_id number,
    raza varchar2(50),
    nombre varchar2(50),
    vet ref veterinario
);
/

create table veterinarios of veterinario;
create table mascotas of mascota;

--A
insert into veterinarios values(1, 'jesus perez', 'c/ el mareo, 29');
/

--B
insert into mascotas values(
    1, 'perro', 'sprunki', (select ref(v) from veterinarios v where v.vet_id = 1)
);

--C
select mas_id from mascotas;

--D
select raza, nombre, vet from mascotas;

--E
select m.nombre, m.raza, deref(m.vet).nombre from mascotas m;

--F
drop table mascotas;
drop table veterinarios;
drop type mascota;
drop type veterinario;