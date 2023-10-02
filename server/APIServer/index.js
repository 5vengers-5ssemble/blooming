const express = require("express");
const app = express();
const cors = require("cors");
app.use(cors());
const dotenv = require("dotenv");
const axios = require("axios");

const PORT = 8084;

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

dotenv.config();

app.post("/data", async (req, res) => {
  try {
    const { startDate, endDate, timeUnit, keywordGroups } = req.body;
    const request_body = {
      startDate: startDate,
      endDate: endDate,
      timeUnit: timeUnit,
      keywordGroups: keywordGroups,
    };
    console.log(req.body);

    const url = "https://openapi.naver.com/v1/datalab/search";
    const headers = {
      "Content-Type": "application/json",
      "X-Naver-Client-Id": process.env.CLIENT_ID,
      "X-Naver-Client-Secret": process.env.CLIENT_SECRET,
    };
    const result = await axios.post(url, request_body, {
      headers: headers,
    });
    console.log(result.data);

    return res.json(result.data);
  } catch (error) {
    console.log(error);
    return res.json(error);
  }
});

app.listen(PORT, () => console.log(`this server ${PORT}`));
