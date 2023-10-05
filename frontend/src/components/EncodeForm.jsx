import React, { useRef, useState } from "react";
import styles from "../css/Form.module.css";
import { fetchData } from "../helpers/common";

function EncodeForm() {
  const offsetRef = useRef();
  const textRef = useRef();
  const [encodedPhrase, setEncodedPhrase] = useState("");

  async function handleEncode(event) {
    event.preventDefault();
    try {
      const { ok, data } = await fetchData(
        "/protected/encoder",
        localStorage.getItem("access"),
        "POST",
        {
          offset: offsetRef.current.value.toUpperCase(),
          plainText: textRef.current.value.toUpperCase(),
        }
      );
      if (ok) {
        setEncodedPhrase(data);
      } else {
        throw new Error(data);
      }
    } catch (error) {
      alert("Error encoding");
    }
  }

  return (
    <form onSubmit={handleEncode}>
      <div className={styles.input_grid}>
        <label htmlFor="offset">Offset Character: </label>
        <input
          type="text"
          id="offset"
          ref={offsetRef}
          placeholder="Enter a single character"
          minLength={1}
          maxLength={1}
          required
        />
        <label htmlFor="text">Phrase to encode: </label>
        <input
          type="text"
          id="text"
          ref={textRef}
          placeholder="Enter text to encode (will be capitalized)"
          required
        />
        <input type="submit" value="Encode" />
      </div>
      <h4>Encoded phrase: {encodedPhrase}</h4>
    </form>
  );
}

export default EncodeForm;
