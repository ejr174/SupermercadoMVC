# 🏪 SupermercadoMVC — Taller Implementación MVC en Java

**Curso:** Plataformas de Desarrollo de Software  
**Semana:** 3  
**Autor:** Emmanuel Jaramillo (ejr174)  

---

## 📌 Descripción

Aplicación de escritorio en Java que implementa el patrón arquitectónico **Modelo-Vista-Controlador (MVC)** para consultar empleados de un supermercado filtrando por cargo.

---

## 🗂️ Estructura del Proyecto

src/main/java/com/supermercado/
│
├── model/
│   ├── Empleado.java              # Entidad: datos de cada empleado
│   └── EmpleadoRepositorio.java   # Repositorio en memoria (12 empleados)
│
├── view/
│   └── EmpleadoVista.java         # GUI con Swing: tabla + filtro por cargo
│
├── controller/
│   └── EmpleadoControlador.java   # Lógica de conexión Vista ↔ Modelo
│
└── SupermercadoMVC.java           # Main: punto de entrada

---

## ⚙️ Tecnologías

| Herramienta | Versión |
|---|---|
| Java | 17 (LTS) |
| Java Swing | Incluido en JDK |
| Apache NetBeans IDE | 17 |
| Maven | Incluido en NetBeans |

---

## 🚀 Cómo ejecutar

1. Clonar el repositorio:
```bash
   git clone https://github.com/ejr174/SupermercadoMVC.git
```
2. Abrir en **NetBeans 17**: File → Open Project
3. Clic derecho sobre el proyecto → **Run**

---

## 🔍 Cargos disponibles para filtrar

| Cargo | Empleados |
|---|---|
| Cajero | 3 |
| Reponedor | 3 |
| Supervisor | 2 |
| Gerente | 2 |
| Seguridad | 2 |

---

## 🏛️ Arquitectura MVC
┌─────────────┐     eventos      ┌──────────────────────┐
│             │ ───────────────► │                      │
│    VISTA    │                  │    CONTROLADOR       │
│ (GUI Swing) │ ◄─────────────── │ (EmpleadoControlador)│
│             │  actualiza tabla  │                      │
└─────────────┘                  └──────────┬───────────┘
│ consulta
▼
┌──────────────────────┐
│       MODELO         │
│ (EmpleadoRepositorio)│
└──────────────────────┘