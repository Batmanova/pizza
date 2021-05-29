CREATE TABLE public."shift" (
                                id serial primary key,
                                dates date NOT NULL,
                                is_second boolean NOT NULL
);
CREATE TABLE public.cheque (
                               dish_id integer NOT NULL,
                               order_id integer NOT NULL,
                               amount integer
);
CREATE TABLE public.dish (
                              id serial primary key,
                             name text NOT NULL,
                             price integer NOT NULL
);
CREATE TABLE public.document (
                                 employee_id integer NOT NULL,
                                 passport text,
                                 inila text,
                                 id_military text
);
CREATE TABLE public.employee (
                                 id serial primary key,
                                 fc_s text NOT NULL,
                                 job_name text NOT NULL,
                                 phone text NOT NULL,
                                 password text NOT NULL
);
CREATE TABLE public."order" (
                                id int not null ,
                                employee_id integer NOT NULL,
                                summ numeric,
                                name varchar(25),
                                is_ready boolean NOT NULL,
                                is_active boolean NOT NULL
);
CREATE TABLE public.shift_workers (
                                      employee_id integer NOT NULL,
                                      shift_id integer NOT NULL
);
INSERT INTO public."shift" (id, dates, is_second) VALUES (0, '2021-06-01', false);
INSERT INTO public."shift" (id, dates, is_second) VALUES (1, '2021-06-01', true);
INSERT INTO public.cheque (dish_id, order_id, amount) VALUES (0, 0, 1);
INSERT INTO public.cheque (dish_id, order_id, amount) VALUES (1, 0, 2);
INSERT INTO public.cheque (dish_id, order_id, amount) VALUES (1, 1, 20);
INSERT INTO public.cheque (dish_id, order_id, amount) VALUES (0, 1, 20);
INSERT INTO public.dish (id, name, price) VALUES (0, 'pizza', 500);
INSERT INTO public.dish (id, name, price) VALUES (1, 'juice', 100);
INSERT INTO public.employee (id, fc_s, job_name, phone, password) VALUES (0, 'John Green', 'cashier', '88005553535', '12345');
INSERT INTO public.employee (id, fc_s, job_name, phone, password) VALUES (1, 'Miranda Black', 'manager', '88005553536', '12345');
INSERT INTO public."order" (id, employee_id, summ, is_ready, is_active) VALUES (0, 0, 700, false, false);
INSERT INTO public."order" (id, employee_id, summ, is_ready, is_active) VALUES (1, 0, 120000, false, true);
INSERT INTO public.shift_workers (employee_id, shift_id) VALUES (0, 0);
INSERT INTO public.shift_workers (employee_id, shift_id) VALUES (0, 1);
INSERT INTO public.shift_workers (employee_id, shift_id) VALUES (1, 1);
INSERT INTO public.shift_workers (employee_id, shift_id) VALUES (1, 0);
INSERT INTO public.document(employee_id, passport, inila, id_military) VALUES (0,'asd','asd','sdf');
ALTER TABLE ONLY public.cheque
    ADD CONSTRAINT cheque_pkey PRIMARY KEY (dish_id, order_id);
ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (employee_id);
ALTER TABLE ONLY public.shift_workers
    ADD CONSTRAINT shift_workers_pkey PRIMARY KEY (worker_id, shift_id);
ALTER TABLE ONLY public."order"
    ADD CONSTRAINT cashier_id FOREIGN KEY (employee_id) REFERENCES public.employee(id) ON DELETE CASCADE;
ALTER TABLE ONLY public.cheque
    ADD CONSTRAINT cheque_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id);
ALTER TABLE ONLY public.cheque
    ADD CONSTRAINT cheque_order_id_fkey FOREIGN KEY (order_id) REFERENCES public."order"(id);
ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employee(id);
ALTER TABLE ONLY public.shift_workers
    ADD CONSTRAINT shift_workers_shift_id_fkey FOREIGN KEY (shift_id) REFERENCES public."shift"(id);
ALTER TABLE ONLY public.shift_workers
    ADD CONSTRAINT shift_workers_worker_id_fkey FOREIGN KEY (worker_id) REFERENCES public.employee(id);
