import React, { useRef, useState } from "react";
import styles from "../css/Form.module.css";
import { fetchData } from "../helpers/common";

function DecodeForm() {
  const offsetRef = useRef();
  const textRef = useRef();
  const [decodedPhrase, setDecodedPhrase] = useState("");

  async function handleDecode(event) {
    event.preventDefault();
    try {
      const { ok, data } = await fetchData(
        "/protected/encoder",
        localStorage.getItem("access"),
        "PATCH",
        {
          plainText:
            offsetRef.current.value.toUpperCase() +
            textRef.current.value.toUpperCase(),
        }
      );
      if (ok) {
        setDecodedPhrase(data);
      } else {
        throw new Error(data);
      }
    } catch (error) {
      console.log(error.message);
      alert("Error encoding");
    }
  }

  return (
    <form onSubmit={handleDecode}>
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
        <label htmlFor="text">Phrase to decode: </label>
        <input
          type="text"
          id="text"
          ref={textRef}
          placeholder="Enter text to decode (will be capitalized)"
          required
        />
        <input type="submit" value="Decode" />
      </div>
      <h4>Decoded phrase: {decodedPhrase}</h4>
    </form>
  );
}

export default DecodeForm;
