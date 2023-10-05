import React, { useState } from "react";
import styles from "../css/Encoder.module.css";
import EncodeForm from "./EncodeForm";
import DecodeForm from "./DecodeForm";

function Encoder() {
  const [doEncode, setDoEncode] = useState(true);
  return (
    <div>
      <div className={styles.switch_row}>
        <div className={styles.switch_container}>
          <div
            className={`${styles.switch_back} ${
              doEncode ? styles.switch1 : styles.switch2
            }`}
            onClick={() => setDoEncode(!doEncode)}
          >
            <input
              type="checkbox"
              className={styles.checkbox}
              checked={doEncode}
              onChange={setDoEncode}
            />
            <span className={styles.switch} />
          </div>
        </div>
        <h3 className={styles.encode_text}>{doEncode ? "Encode" : "Decode"}</h3>
      </div>
      {doEncode ? (
        <div>
          <EncodeForm />
        </div>
      ) : (
        <div>
          <DecodeForm />
        </div>
      )}
    </div>
  );
}

export default Encoder;
