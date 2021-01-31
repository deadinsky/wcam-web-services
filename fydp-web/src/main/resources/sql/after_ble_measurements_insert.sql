DELIMITER $$
CREATE TRIGGER after_ble_measurements_insert
AFTER INSERT
ON ble_measurements FOR EACH ROW
BEGIN
    DECLARE h_tx_power double;
    DECLARE h_signal_propagation_constant double;
    SELECT h.tx_power INTO h_tx_power FROM hubs h WHERE h.id = NEW.hub_id;
    SELECT h.signal_propagation_constant INTO h_signal_propagation_constant FROM hubs h WHERE h.id = NEW.hub_id;
    IF NEW.strength IS NOT NULL THEN
        INSERT INTO wristband_locations(wristband_id, location_X, location_Y, location_Z, time_stamp, approximate_distance_in_metres)
        SELECT NEW.wristband_id, hl.location_X, hl.location_Y, hl.location_Z, NEW.time_stamp,
        CASE WHEN h_tx_power IS NULL OR h_signal_propagation_constant IS NULL THEN NULL ELSE
        POWER(10, ((h_tx_power - NEW.strength)/(10 * h_signal_propagation_constant)))
        END
        FROM hub_locations hl WHERE hl.hub_id = NEW.hub_id ORDER BY hl.time_stamp DESC, id DESC LIMIT 1;
    END IF;
END$$
DELIMITER ;
