-- ============================================
-- 1. SCHEMA CREATION
-- ============================================

CREATE TABLE student (
    id INT,
    name VARCHAR(50)
);

CREATE TABLE order_detail (
    id VARCHAR(50),
    student_id INT,
    order_date DATE,
    truck_id VARCHAR(50)
);

CREATE TABLE flavor (
    id VARCHAR(50),
    order_id VARCHAR(50),
    name VARCHAR(50)
);

-- ============================================
-- 2. SAMPLE DATA
-- ============================================

-- Students
INSERT INTO student (id, name) VALUES (1, 'Florenza Branchflower');
INSERT INTO student (id, name) VALUES (2, 'Lorry Domange');
INSERT INTO student (id, name) VALUES (3, 'Lorenza Hundey');

-- Orders (with truck_id added)
INSERT INTO order_detail (id, student_id, order_date, truck_id)
VALUES ('o20', 1, '2023-12-03', 'T1');
INSERT INTO order_detail (id, student_id, order_date, truck_id)
VALUES ('o8', 2, '2023-12-11', 'T2');
INSERT INTO order_detail (id, student_id, order_date, truck_id)
VALUES ('o1', 3, '2023-11-09', 'T1');

-- Add another order for student 1 on a different truck
INSERT INTO order_detail (id, student_id, order_date, truck_id)
VALUES ('o30', 1, '2023-12-15', 'T2');

-- Flavors
INSERT INTO flavor (id, order_id, name) VALUES ('f20', 'o20', 'Chocolate Chip Cookie Dough');
INSERT INTO flavor (id, order_id, name) VALUES ('f12', 'o20', 'Strawberry');
INSERT INTO flavor (id, order_id, name) VALUES ('f14', 'o20', 'French Vanilla');
INSERT INTO flavor (id, order_id, name) VALUES ('f1',  'o18', 'Rum Raisin'); -- orphan order
INSERT INTO flavor (id, order_id, name) VALUES ('f10', 'o20', 'Chocolate');
INSERT INTO flavor (id, order_id, name) VALUES ('f30', 'o30', 'Mint Chocolate Chip');

-- ============================================
-- 3. QUERIES
-- ============================================

-- 1. How many flavors has each student bought within a date range?
SELECT
    s.id AS student_id,
    s.name AS student_name,
    COUNT(DISTINCT f.id) AS flavor_count
FROM student s
JOIN order_detail o ON s.id = o.student_id
JOIN flavor f ON o.id = f.order_id
WHERE o.order_date BETWEEN '2023-10-01' AND '2023-12-31'
GROUP BY s.id, s.name;

-- 2. For each flavor, count number of students who bought it
SELECT
    f.name AS flavor,
    COUNT(DISTINCT o.student_id) AS student_count
FROM flavor f
JOIN order_detail o ON f.order_id = o.id
GROUP BY f.name;

-- 3. Students with at least 2 flavors
SELECT
    s.id AS student_id,
    s.name AS student_name,
    COUNT(DISTINCT f.id) AS flavor_count
FROM student s
JOIN order_detail o ON s.id = o.student_id
JOIN flavor f ON o.id = f.order_id
GROUP BY s.id, s.name
HAVING COUNT(DISTINCT f.id) >= 2;

-- 4. Group “Chocolatey” flavors together, then filter students with >=2 flavor groups
SELECT
    s.id AS student_id,
    s.name AS student_name,
    COUNT(DISTINCT f.id) AS flavor_count
FROM student s
JOIN order_detail o ON s.id = o.student_id
JOIN flavor f ON o.id = f.order_id AND f.name LIKE 'Chocolate%'
GROUP BY s.id, s.name
HAVING COUNT(DISTINCT f.id) >= 2;

-- 5. Students with orders from more than 1 truck
SELECT
    s.id AS student_id,
    s.name AS student_name,
    COUNT(DISTINCT o.truck_id) AS truck_count
FROM student s
JOIN order_detail o ON s.id = o.student_id
GROUP BY s.id, s.name
HAVING COUNT(DISTINCT o.truck_id) > 1;
