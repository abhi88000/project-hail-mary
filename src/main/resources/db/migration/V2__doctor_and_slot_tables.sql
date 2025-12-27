-- ==========================
-- DOCTOR TABLE
-- ==========================
CREATE TABLE doctor (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    CONSTRAINT fk_doctor_tenant FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);

CREATE INDEX idx_doctor_tenant ON doctor(tenant_id);

-- ==========================
-- SLOT TABLE
-- ==========================
CREATE TABLE slot (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    tenant_id UUID NOT NULL,
    doctor_id UUID NOT NULL,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_slot_tenant FOREIGN KEY (tenant_id) REFERENCES tenant(id),
    CONSTRAINT fk_slot_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

CREATE INDEX idx_slot_doctor_date ON slot(doctor_id, date);
