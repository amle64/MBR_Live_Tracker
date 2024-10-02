<template>
  <div>
    <h1>Upload MBR List (Excel File)</h1>
    <!-- Input to upload the Excel file -->
    <input type="file" @change="handleFileUpload" accept=".xlsx, .xls" />

    <!-- Table to display the MBR list -->
    <table v-if="mbrList.length > 0">
      <thead>
        <tr>
          <th v-for="(header, index) in tableHeaders" :key="index">{{ header }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(row, index) in mbrList" :key="index">
          <td v-for="(cell, cellIndex) in row" :key="cellIndex">{{ cell }}</td>
        </tr>
      </tbody>
    </table>

    <!-- Confirm button to upload data -->
    <button @click="confirmUpload" v-if="selectedFile">Confirm Upload</button>

    <!-- Confirmation Modal-->
    <div class="modal-overlay" v-if="showConfirmationModal">
      <div class="modal">
        <p> Are you sure you want to upload this Excel File? </p>
        <button v-on:click="uploadFile">Yes</button>
        <button v-on:click="showConfirmationModal = false">No</button>
      </div>
    </div>
  </div>
</template>

<script>
import * as XLSX from 'xlsx';
import MbrService from '../services/MbrService'; // Import the service

export default {
  data() {
    return {
      mbrList: [], // Store the MBR list from the Excel file
      tableHeaders: [], // Store table headers (e.g., column names)
      selectedFile: null,
      showConfirmationModal: false
    };
  },
  methods: {
    handleFileUpload(event) {
      this.selectedFile = event.target.files[0];
      const reader = new FileReader();

      reader.onload = (e) => {
        const binaryStr = e.target.result;
        const workbook = XLSX.read(binaryStr, { type: 'binary' });
        const firstSheet = workbook.Sheets[workbook.SheetNames[0]];
        const jsonData = XLSX.utils.sheet_to_json(firstSheet, { header: 1 });

        if (jsonData.length) {
          this.tableHeaders = jsonData[0]; // First row for headers (e.g., product_name, total_quantity_issued, date_created)

          this.mbrList = jsonData.slice(1).map((row) => {
            return row.map((cell, index) => {
              const columnName = this.tableHeaders[index];

              // Convert 'total_quantity_issued' to Integer
              if (columnName === 'total_quantity_issued' && typeof cell === 'string') {
                const parsed = parseInt(cell, 10);
                return isNaN(parsed) ? cell : parsed; // Return integer or original cell if it's not a valid number
              }

              // Convert 'date_created' to a proper date format if it's in Excel date serial number
              if (columnName === 'date_created' && typeof cell === 'number') {
                return this.excelDateToJSDate(cell).toLocaleDateString('en-US', {
                  year: 'numeric',
                  month: '2-digit',
                  day: '2-digit',
                });
              }

              // Otherwise, return the string value for 'product_name' and other columns
              return cell;
            });
          });
        }
      };

      reader.readAsBinaryString(this.selectedFile);
    },


    // Function to convert Excel serial date to JS date
    excelDateToJSDate(serial) {
      const daysSince1900 = serial - 25569; // Excel counts dates starting from 1/1/1900
      const millisecondsInDay = 86400 * 1000; // 86400 seconds in a day
      return new Date(daysSince1900 * millisecondsInDay);
    },
    confirmUpload() {
      // Show confirmation modal
      this.showConfirmationModal = true;
    },
    async uploadFile() {
      try {

        const response = await MbrService.uploadMbr(this.selectedFile, this.$store.state.token);
        console.log(response.data);
        alert("File uploaded successfully");
        this.showConfirmationModal = false;

      } catch (error) {
        console.error(error);
        alert("Failed to upload the file");
      }
    }


  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal{
  background-color: white;
  padding: 1rem 2rem;
  border-radius: 5px;
  text-align: center;
}
</style>
