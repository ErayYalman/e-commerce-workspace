CREATE TABLE outbox_events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    aggregate_id UUID NOT NULL, -- aggregate_id, event'in hangi aggregate'e ait olduğunu belirtir
    aggregate_type VARCHAR(100) NOT NULL, -- aggregate_type, event'in hangi aggregate türüne ait olduğunu belirtir
    payload JSONB NOT NULL, -- payload, event ile ilgili mesaj içeriğini JSON formatında saklar
    status VARCHAR(50) NOT NULL DEFAULT 'pending' CHECK (status IN ('pending', 'published', 'failed')), -- event'in işlenme durumu (PENDING → PUBLISHED → FAILED)
    created_at TIMESTAMPTZ DEFAULT now()
);

CREATE INDEX idx_outbox_status_pending ON outbox_events(status) WHERE status = 'pending'; -- pending durumundaki event'leri hızlıca bulmak için index
