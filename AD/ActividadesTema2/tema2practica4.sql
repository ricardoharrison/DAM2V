--1 & 2
create or replace type triangulo as object(
    base number,
    altura number,
    member function area return number
) not final;
/

create or replace type body triangulo as member function area return number is
    begin
        return (base * altura) / 2;
    end area;
end;
/

--3
create table triangulos(
    id_tri number,
    tri triangulo
);

--4
insert into triangulos values(1, triangulo(5, 5));
insert into triangulos values(2, triangulo(10, 10));

--5
select t.id_tri, t.tri.base, t.tri.altura from triangulos t;

--6
begin
    for i in (select * from triangulos) loop
        dbms_output.put_line('El triangulo con id = ' || i.id_tri);
        dbms_output.put_line('con base = ' || i.tri.base);
        dbms_output.put_line('y altura = ' || i.tri.altura);
        dbms_output.put_line('tiene un area de = ' || i.tri.area);
    end loop;
end;
/