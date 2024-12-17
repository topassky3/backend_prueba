-- V1__initial_schema.sql

-- NOTA: Se ha removido la creación de la extensión 'vector' para evitar dependencias adicionales.
--       Si necesitas almacenar embeddings, utilizaremos un arreglo de double precision (float8) en su lugar.

-- Crear el esquema meta si no existe
CREATE SCHEMA IF NOT EXISTS meta;

-- Crear tablas en el esquema meta
CREATE TABLE IF NOT EXISTS meta.embeddings (
                                               id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                               created_at TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL,
    content TEXT NOT NULL,
    embedding double precision[] NOT NULL
    );

CREATE TABLE IF NOT EXISTS meta.migrations (
                                               version TEXT PRIMARY KEY,
                                               name TEXT,
                                               applied_at TIMESTAMP WITH TIME ZONE DEFAULT now() NOT NULL
    );

-- Crear otras tablas en el esquema public
CREATE TABLE IF NOT EXISTS public.user_groups (
                                                  id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                                  name TEXT NOT NULL UNIQUE,
                                                  privileges TEXT[] NOT NULL
);

CREATE TABLE IF NOT EXISTS public.users (
                                            id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                            username TEXT NOT NULL,
                                            email TEXT NOT NULL UNIQUE,
                                            password TEXT NOT NULL,
                                            created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    group_id BIGINT,
    CONSTRAINT users_group_id_fkey FOREIGN KEY (group_id) REFERENCES public.user_groups(id)
    );

CREATE TABLE IF NOT EXISTS public.tasks (
                                            id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                            user_id BIGINT,
                                            title TEXT NOT NULL,
                                            description TEXT,
                                            status TEXT NOT NULL CHECK (status IN ('pendiente', 'en progreso', 'completada')),
    due_date DATE,
    priority TEXT NOT NULL CHECK (priority IN ('alta', 'media', 'baja')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CONSTRAINT tasks_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id)
    );

-- Crear índices
CREATE INDEX IF NOT EXISTS idx_tasks_priority ON public.tasks USING btree (priority);
CREATE INDEX IF NOT EXISTS idx_tasks_status ON public.tasks USING btree (status);
