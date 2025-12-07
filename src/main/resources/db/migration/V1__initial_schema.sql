-- 1. Enable UUID extension (Critical for UUID generation)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 2. CLEANUP: Drop tables in reverse order of dependency (Safety for Dev environment)
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS tenant;

-- ==========================
-- TENANT TABLE
-- ==========================
CREATE TABLE tenant (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) UNIQUE NOT NULL,
    industry VARCHAR(255),
    plan VARCHAR(50),
    timezone VARCHAR(100),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- ==========================
-- PATIENT TABLE
-- ==========================
CREATE TABLE patient (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id UUID NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    CONSTRAINT fk_patient_tenant FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);

CREATE INDEX idx_patient_tenant ON patient(tenant_id);

-- ==========================
-- APPOINTMENT TABLE
-- ==========================
CREATE TABLE appointment (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id UUID NOT NULL,
    patient_id UUID NOT NULL,
    appointment_time TIMESTAMPTZ NOT NULL,
    status VARCHAR(50) DEFAULT 'scheduled',
    notes TEXT,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    CONSTRAINT fk_appt_tenant FOREIGN KEY (tenant_id) REFERENCES tenant(id),
    CONSTRAINT fk_appt_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE INDEX idx_appt_tenant ON appointment(tenant_id);
CREATE INDEX idx_appt_patient ON appointment(patient_id);

-- ==========================
-- SEED DATA (Optional: To Verify it works)
-- ==========================
INSERT INTO tenant (name, slug, industry, plan) VALUES
('Smile Dental', 'smile-dental', 'Dental', 'Pro');