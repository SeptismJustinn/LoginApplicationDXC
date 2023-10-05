export async function fetchData(endpoint, token, method, body) {
  const res = await fetch(import.meta.env.VITE_SERVER + endpoint, {
    method,
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token,
    },
    body: JSON.stringify(body),
  });
  const data = await res.json();

  let returnValue = {};

  if (res.ok) {
    if (!data.status) {
      returnValue = { ok: false, data: data.message };
    } else {
      returnValue = { ok: true, data: data.content };
    }
  } else {
    if (data?.message) {
      returnValue = { ok: false, data: data.message };
    } else {
      returnValue = { ok: false, data: "An error has occurred" };
    }
  }
  return returnValue;
}
