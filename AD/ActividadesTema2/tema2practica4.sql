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


select id_tri, tri.base, tri.altura from triangulos;