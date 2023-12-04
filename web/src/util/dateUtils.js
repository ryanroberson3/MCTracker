// dateUtils.js

/**
 * Format a date represented as an array [YYYY, M, D], [YYYY, MM, D], or [YYYY, MM, DD]
 * to "MM-DD-YYYY" format.
 * @param {Array} dateArray - The date array to format [YYYY, M, D], [YYYY, MM, D], or [YYYY, MM, DD].
 * @returns {string} - The formatted date string ("MM-DD-YYYY").
 */
export function formatDateToMMDDYYYY(dateArray) {
    const year = dateArray[0];
    const month = dateArray[1];
    const day = dateArray[2];
  
    // Ensure month and day are zero-padded to 2 digits
    const formattedMonth = month.toString().padStart(2, '0');
    const formattedDay = day.toString().padStart(2, '0');
  
    // Format the date as "MM-DD-YYYY"
    // const formattedDate = `${formattedMonth}-${formattedDay}-${year}`;
    const formattedDate = `${year}-${formattedMonth}-${formattedDay}`;
  
    return formattedDate;
  }
  

  // Parse the date from MM-DD-YYYY format
// Parse the date from MM-DD-YYYY format
// Parse the date from MM-DD-YYYY format
export function parseDateFromMMDDYYYY(dateStringOrArray) {
  console.log('Received input:', dateStringOrArray);

  if (Array.isArray(dateStringOrArray)) {
      // If it's already an array, return it
      return dateStringOrArray;
  }

  if (typeof dateStringOrArray !== 'string') {
      throw new Error('Invalid date format: Input is neither a string nor an array');
  }

  const dateParts = dateStringOrArray.split('-').map(Number);

  // Check if the parsed values are valid
  if (dateParts.some(isNaN)) {
      throw new Error('Invalid date format: Cannot parse parts of the date');
  }

  return dateParts;
}


