SELECT 'GRANT SELECT ON ' || relname || ' TO webuser;' || ' ' ||
       'GRANT DELETE ON ' || relname || ' TO webuser;' || ' ' ||
       'GRANT INSERT ON ' || relname || ' TO webuser;' || ' ' ||
       'GRANT UPDATE ON ' || relname || ' TO webuser;'
 FROM pg_class JOIN pg_namespace ON pg_namespace.oid = pg_class.relnamespace
WHERE nspname = 'public' AND relkind IN ('r', 'v', 'S');
