CREATE FUNCTION calculate_rewards_points(purchase_amount bigint)
    RETURNS INTEGER AS $$
DECLARE
    points INTEGER := 0;
BEGIN
    IF purchase_amount > 100 THEN
        points := points + (purchase_amount - 100) * 2;
        purchase_amount := 100;
    END IF;
    IF purchase_amount >= 50 THEN
        points := points + (purchase_amount - 50);
    END IF;
    RETURN points;
END;
$$ LANGUAGE plpgsql;